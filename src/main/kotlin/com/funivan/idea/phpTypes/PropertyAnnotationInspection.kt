package com.funivan.idea.phpTypes

import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.jetbrains.php.lang.inspections.PhpInspection
import com.jetbrains.php.lang.psi.elements.PhpClass
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor


class PropertyAnnotationInspection : PhpInspection() {
    override fun getShortName() = "PropertyAnnotationInspection"
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PhpElementVisitor() {
            override fun visitPhpClass(clazz: PhpClass) {
                val properties = clazz.fields.filter { it.isPhysical && it.modifier.isPrivate }
                if (clazz.constructor === null) {
                    for (property in properties) {
                        val nameNode = property.nameNode
                        if (nameNode != null && property.children.size === 0) {
                            val comment = property.docComment
                            if (comment == null) {
                                holder.registerProblem(
                                        nameNode.psi,
                                        "Property is not annotated correctly"
                                )
                            }
                        }
                    }
                }

                super.visitPhpClass(clazz)
            }
        }
    }
}