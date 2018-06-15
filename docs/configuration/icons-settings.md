---
layout: docs
title: Icons Settings
description: Change the IDE's icons
group: configuration
toc: true
comments: true

previous:
  url: '/docs/configuration/panel-settings'
  title: Panel Settings
next:
  url: '/docs/configuration/project-view-settings'
  title: Project View Settings

---

These settings control some components' display, mainly about their compactiness.
{:class='title'}

{% include carbonads.html %}

----
### Material Icons

This setting toggles the replacement of the icons provided by the plugin. Specifically this disables:
- File Icons
- Menu Icons
- Toolbar Icons
- Tool Window Icons
- Application Icons

Basically this replaces all icons provided by the plugin, including breakpoint icons, dialog icons and even icons inside documenation panels.

{% include figure.html content="/screens/iconsDisabled.png" caption="Material Icons Disabled" %}

**Note**: Because this option replaces IDE icons, a complete restart of the application is needed.

**Note 2**: Some of the features depend on this setting to work, therefore disabling this will result to disabling those features as well. These are:
- _Hide File Icons_: You won't be able to hide file icons anymore.

----
### Monochrome Icons

This option allows you to apply a monochrome filter to the whole IDE, just like the color blindness filter. This is ideal for people who don't like the avalanche of colors provided by the different icons.

{% include figure.html content="/screens/monochrome.png" caption="Monochrome Icons" %}

By applying this filter, all icons will be filtered with a shade of the primary color (e.g. the color of the items in the Project View for instance) of the current theme.

Note: This setting not only filters out the toolbar and file icons, but even tool window icons, debugger icons, etc...
{:class='card-panel warn'}

----
### Hide File Icons

This option allows you to _completely hide_ the file icons from the IDE. This means from the _Project Tree_, Tabs, Dialogs and everything. This does not remove folders, though.

{% include figure.html content="/screens/hideFileIcons.png" caption="Hide File Icons" %}

**Note**: This feature takes precedence over default file icons providers and the plugin's own one. However some plugins, such as the _PHP_ or _Python_ plugins, may have an even greater precedence, meaning that you will still see icons from these plugins.
{:class='card-panel'}

----
### Folder Decorators

Assign specific folder icons to folders with commonly used names to add yet more visual grepping than with file icons. For instance, `tmp`, `logs`, `src`, `lib` or `test` are folders found within most projects.

{% include figure.html content="/screens/decoFolders.png" caption="Decorated Folders" %}

Note that excluded/resource root/test root/package folders will not be decorated.

----
### Notes

- While the Material Icons replace most icons, some icons, such as icons coming from plugins (including Jetbrains' plugins like Git) would not be replaced.
- Monochrome icons actually messes up some parts of the IDE, such as the SVG Image Viewer. If you need to use it, please disable the option temporarily.

