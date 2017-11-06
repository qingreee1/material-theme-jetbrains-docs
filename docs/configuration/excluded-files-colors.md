---
layout: docs
title: Excluded Files Colors
description: Learn about File Colors, Scopes and Excluded files
group: configuration
toc: true
colors:
  - name: default
    color: '#2E3C43'
    light: false
  - name: darker
    color: '#323232'
    light: false
  - name: lighter
    color: '#EAE8E8'
    light: true
  - name: palenight
    color: '#2F2E43'
    light: false
---

## Introduction

Jetbrains editors are really great for all kind of projects, whatever big they are. However, once you've started working on big projects, you're beginning to notice that it's getting harder finding your way through open files, the project tree, the search results and so on.

Therefore Jetbrains introduced a nifty feature called **File Colors**. It allows developers to add custom colors to specific groups of files according to a pattern.
For example, it could be:
- Excluded Files
- Tests
- Erroneous files
- Build Files
- Logs
- etc...

You can even set specific folders representing different parts/modules of your project.

These colors will be then displayed in different parts of the IDE, namely:
- Project Tree
- Editor Tabs
- Find in Path Dialog
- Search Everywhere dialog
- Navigate to file/class/symbol dialog
- And other places...

This will considerably reduce the time looking for a file and navigating thanks to *color grepping*.

## Configuration

The Jetbrains editors come prebundled with at least two File Colors:
- **Excluded files**, e.g. files that are _Marked as Excluded_, and therefore excluded for indexing, searching and navigation, resulting in huge performance boosts. Such examples are `node_modules`, `logs`, `vendors`, `gradle` etc... You can mark a directory for exclusion by clicking right on the directory and select `Mark Directory as...`
- **Tests**, which is the test directory in gradle/rails/symfony...

Specific IDEs might have other File Colors preinstalled.

You can see them in `Settings -> Appearance & Behavior -> File Colors`.

{% include figure.html content="/screens/fileColors.png" caption="File Colors" %}

There you can add new Local colors, share them across projects, disable them on the project view/tabs/both, and last but not least you can reorder them to give precedence to one in case two or more file colors are falling in the same pattern rules.

### Scopes

However these two options are pretty limited, but you can add your own file colors according to certain patterns. For example, differentiating between the `docs` directory than the rest of the source.

To do so, one must create a **Scope**. There you can define your pattern rules, which directories are included in the scope, which are excluded, and so on. Finally add your new created scope in the File Colors list, and rise it up to the top so it won't be shrouded by previous file colors.

{% include figure.html content="/screens/scopes.png" caption="Scopes" %}

See more at [File Colors](https://www.jetbrains.com/help/idea/2017.3/file-colors.html?utm_medium=help_link&utm_source=from_product&utm_campaign=IU&utm_content=2017.3)

-----
## Material File Colors

This is pretty neat, but the actual prebundled File Colors are adapted for the _Darcula/IntelliJ_ look and feels, not for the Material Themes.

To remedy this, the Material Theme is also coming prebundled with File Colors, specifically for ++Excluded files++.

However, because the _File Colors_ are user settings, and because the user could very possibly have modified the default Excluded Files' file colors, the plugin could not override this setting with a better suited color. Therefore, the plugin is bundled with **additional** excluded files colors, one for each theme.

{% include figure.html content="/screens/materialfilecolors.png" caption="Material Non Project Files" %}

As a consequence, switching the theme will **NOT** set its own _Excluded Files Color_ to the top, which may lead to a UI hard to read. Simply put the color related to your selected theme at the top and everything will be alright.

-----
## FAQ

**Q**: I've switched to Darker Theme and now my Project Tree is very hard to read!

**A**: As I said, simply put the Darker Excluded Files Color at the top using the arrows, then save.

**Q**: What happens if I remove/modify the bundled colors?

**A**: If you remove the colors, they will be added back at next restart. Also if you modify them, the plugin will add back the default color besides your copy, so please bear that in mind.

**Q**: Is there a way to change the text color?

**A**: The color of the text is controlled by another setting. See [File Status Colors](file-status-colors.md) for more info.

-----
## Color Reference

{% for color in page.colors %}
{% include color.html color=color %}
{% endfor %}
