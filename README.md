# KLib
Kotlin library containing useful Kotlin functions for my personal projects.

***
<h3>Add implementation using gradle</h2>

**settings.gradle**:
```
sourceControl {
    gitRepository(URI.create('https://github.com/BlockyBorzan/KLib.git')) {
        producesModule('me.blocky.lib.klib:KLib')
    }
}
```
**build.gradle**:
```
implementation 'me.blocky.lib.klib:KLib:+'
```