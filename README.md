# PhpClean - PhpStorm/IDEA plugin  

![Build Status](https://img.shields.io/travis/com/funivan/PhpClean.svg?style=flat-square)
![Version](https://img.shields.io/jetbrains/plugin/v/11272.svg?style=flat-square)
![Downloads](https://img.shields.io/jetbrains/plugin/d/11272.svg?style=flat-square)
![License](https://img.shields.io/github/license/funivan/PhpClean.svg?style=flat-square)



Static Code Analysis for PhpStorm and Intellij Idea.

## Installation
#### Stable builds
Open IDE go to `Settings->Plugins->Marketplace` search for the `PhpClean`.
Hit `install` button. Restart your IDE.

#### Nightly build - get fresh version after each commit
You can install nightly builds using custom repository.
 
Open IDE go to `Settings->Plugins->Manage plugin repositories` and add repository
`http://funivan.com/ci/PhpClean-nightly.xml`
Then you can install **PhpClean**


![GitHub commits (since latest release)](https://img.shields.io/github/commits-since/funivan/PhpClean/latest.svg?style=flat-square)



## List of inspection:

#### MethodCanBePrivate
Protected methods can be converted to private.
```php
final class A {
  protected function name() {} 
  // ^^^ Method can be private
}
```
#### MethodShouldBeFinal
Public methods should be closed (make method or class final)
```php
class User{
 public function name() : string {
 // ^^^ Method should be final
  return "";
 }
}
```          
#### MethodVisibility 
Protected methods make our classes more open. Write private or public methods only.
```php
class User {
  protected function name() : string {}
  // ^^^ Do not write protected methods. Only public or private
  public function id() : string {}
  private function login() : string {}
}
```
#### MissingParameterTypeDeclaration 
Always specify parameter type. This is a good practice.
```php
function withName($name){}
// ^^^ Missing parameter type
```
#### MissingReturnType
Always specify result type of the function.
```php
class Action {
 /** @return void */
 protected function hide(){}
 // ^^^ Missing return type
}
```

#### PhpCleanUndefinedMethod (experimental)
Try to fix undefined method phpstorm bugs: https://youtrack.jetbrains.com/issue/WI-5223
```php
final class Email { public function send(){}}
/** @var mixed|Email $email */;
$email->send();
$email->show();
// ^^^ Undefined method
```
#### PropertyAnnotation
Properties that are not initialized in the constructor should be annotated as nullable.
```php
class A {
  /** @var string[] */
  private $first;
  // ^^^ Property is not annotated correctly. Add null type
}
```

#### VirtualTypeCheck
Use assert to check variable type instead of doc comment.
```php
class User{}
/** @var $user User */;
// ^^^ Use assert to check variable type
assert($user instanceof User); // Valid
```
                 
