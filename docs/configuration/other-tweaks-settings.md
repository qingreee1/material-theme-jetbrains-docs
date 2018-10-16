---
layout: docs
title: Component Settings
description: Other Material Theme plugin tweaks
group: configuration
comments: true
toc: true

previous:
  url: '/docs/configuration/features-settings'
  title: Features Settings
next:
  url: '/docs/configuration/quick-actions-panel'
  title: Quick Actions Panel
---

These features are additional extras to the plugin, not related to the themes nor Material Design but simply nice addons for the IDE.
{:class = 'title'}

{% include carbonads.html %}

### Project View Decorators

This feature is adding decorations to the Project View to mimic the original Material Sublime Theme decorations.

###### Open and closed directories

When this option is on, files that are _opened_ in the editor and that are _edited_ will have their containing directories have a __hollow directory icon__ (or outline directory) instead of the original icon.

{% include figure.html content="/screens/folderDecorators.png" caption="Opened folder decorators" %}

**Note**: This feature requires *Material Icons* feature to work.

###### Accent colored opened directories

Same as the hollow directory icon, files that are _opened_ and _edited_ will have their containing directories be colored with the selected _accent color_.

----
### Theme in Status Bar

This setting adds a "widget" to the status bar to indicate which theme you are currently on. You can also click on it to open the Material Theme Settings.

{% include figure.html content="/screens/widgetStatusbar.png" caption="Status Bar Widget" %}

Since version 2.9.4/2.10.4 you can also see your current accent color.

{% include figure.html content="/screens/statusAccentWidget.png" caption="Accent Widget" %}

----
### Themed Title Bar

##### Windows 10

This setting also allows the theming of the application title bar in Windows 10. To do so, it is actually setting the [Windows Accent Color](https://www.howtogeek.com/225893/how-to-personalize-windows-10s-appearance/) set by Windows.

**Note**: Since this modifies the color OS-wide, the color set by the plugin will stick even if you close the IDE/uninstall the plugin. Just follow the instructions at https://www.howtogeek.com/225893/how-to-personalize-windows-10s-appearance/ to set it to another color of your choice.

##### Mac OS

From version 2.5.0 the option came back with support for Mac OS. And unlike in Windows, this does not change the Registry but actually applies the background color to the IDE title bar only!

However it comes with a small price: by activating this option you are consenting to nullify the _Dark Window Headers_ feature from the `Appearance` panel introduced since 2018.2.

{% include figure.html content="/screens/titlebarThemed.png" caption="Themed Title Bar Mac" %}

-----
## FAQ/Troubleshooting

**Q**: **I've disabled the plugin but I still get the themed title bar!**

**A**: This happens because this is not a feature implemented by the plugin, but instead makes use of a feature of the operating system. Therefore even when the IDE is closed the setting will persist. You can change the color of the title bar in Windows by following the instructions at https://www.howtogeek.com/225893/how-to-personalize-windows-10s-appearance/

