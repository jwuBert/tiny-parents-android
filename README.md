# 微童年 Android 家长版 客户端

## Quick Start

* Install and configure Android studio
* Git clone this source code
* Gradle sync project
* Build and have fun

## How To Contribute

### Coding Style

Just use Square's coding style tool: https://github.com/square/java-code-styles

And always use lint. Make warning gone.

### Git Commit Guidelines

We have very precise rules over how our git commit messages can be formatted. This leads to more
readable that are easy to follow when looking through the project history. But also, we use the
git commit to generate the App change log.

#### Commit Message Format

Each commit message consists of a header, a body and a footer. The header has a special format
that includes a type, a scope and a subject:

```
<type>(<scope>): <subject>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

Any line of the commit message cannot be longer 100 characters! This allows the message to be
easier to read on Github as well as in various git tools.

Type must be one of the following:

 * *feat*: A new feature
 * *fix*: A bug fix
 * *docs*: Documentation only changes
 * *style*: Changes that do not affect the meaning of the code (white-space, formatting, missing
 semi-colons, etc)
 * *refactor*: A code change that neither fixes a bug or adds a feature
 * *perf*: A code change that improves performance
 * *test*: Adding missing tests
 * *chore*: Changes to the build process or auxiliary tools and libraries such as documentation
 generation

The scope could be anything specifying place of the commit change.
Subject

The subject contains succinct description of the change:

 * use the imperative, present tense: "change" not "changed" nor "changes"
 * don't capitalize first letter
 * no dot (.) at the end

Just as in the subject, use the imperative, present tense: "change" not "changed" nor "changes"
The body should include the motivation for the change and contrast this with previous behavior.

The footer should contain any information about Breaking Changes and is also the place to
reference GitHub issues that this commit Closes.

