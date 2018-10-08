package com.funivan.idea.phpClean.inspections.architecture

import com.funivan.idea.phpClean.BaseInspectionTest

class MissingParameterTypeDeclarationInspectionTest : BaseInspectionTest() {

    fun testFindMethodsThatCanBePrivate() {
        assert(
                MissingParameterTypeDeclarationInspection(),
                """<?php
                function withName(<warning descr="Missing parameter type">${'$'}name</warning>){}
                """
        )
    }
}