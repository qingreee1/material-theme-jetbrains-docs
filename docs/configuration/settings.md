---
layout: docs
title: Settings
description: Learn how to configure the plugin to your tastes
group: configuration
toc: true
---

The Material Theme plugin comes with a bunch of features tailored for an optimal experience. However, it is also highly
configurable to allow each and everyone to customize it to their hearts content. {:class='title'}

## Themes and Color Schemes

### Theme Switcher

The plugin comes prebundled with a set of themes interchangeables through the
![Theme Switcher]({{ site.media_folder | prepend: site.baseurl | replace: '//', '/' }}/icons/switcher.png) *Theme Switcher*.

{% include figure.html content="/screens/switcher.png" caption="Theme Switcher" %}

There are many ways to invoke the *Theme Switcher*:
- From the IDE menu `Tools -> Material Theme -> Material Theme Chooser`
- From the _Main Toolbar_
- From the `Search Everything` dialog, type `Material Theme`
- From the `Quick Switch` panel <kbd>(Ctrl + \`)</kbd> (Windows: <kbd>Ctrl + ~</kbd>)`

{% include figure.html content="/screens/quickswitch.png" caption="Quick Switch" %}

The plugin comes prebundled with {{ site.data.themes.material.size | plus: site.data.themes.other.size }} themes:

#### Material Themes

These themes are based on the Sublime plugin's, made by Mattia Astorino ([@equinusocio](https://github.com/equinusocio))

<ul class="theme-cards">
{% for theme in site.data.themes.material %}
  {% include theme-card.html %}
{% endfor %}
</ul>

#### Other themes

Besides the Material Themes, there are also other prebundled themes made by the community:

<ul class="theme-cards">
{% for theme in site.data.themes.other %}
  {% include theme-card.html %}
{% endfor %}
</ul>

#### Custom Theme

And finally there is a **Custom Theme** option, which is the placeholder for the Custom Theme Settings, allowing you
to change the theme's colors to your convenience.

See [Custom Themes](/docs/custom-themes) for more information.

----------
## Material Theme Settings

You can configure many parts of the plugin features from the **plugin settings**. They are accessible within `Settings
-> Appearance -> Material Theme`.

{% include figure.html content="/screens/settings.png" caption="Material Theme Settings" %}

#### Explanation

**Active Tab Settings**:
- *Active Tab Highlight*: Customize active tab indicator color and thickness (works also for the Project View selected row)
- *Tabs Height*: Set a custom height for tabs (between 25 and 60 pixels)
- *Uppercase Bold Tabs*: Set the Editor Tabs in Bold Upper Case

**Panel Settings**:
* *Contrast Mode*: Add contrast to some of the IDE's components
- *Hide Icons*: Hide file icons (you must have Material Icons options on)
- *Custom Sidebar Height*: Set a custom line height in Project View (min: 18, max: 30)
- *Custom Tree Indent*: Increase or reduce indentation in the sidebar (min: 2, max: 8)
- *Bold directories*: Set bold font weight for directories in the Project View
- *Compact Status Bar*: Reduce the height of the status bar (this is the default height)
- *Custom Accent Color*: Set a custom color for accent color

**Component Settings**:
* *Custom Wallpaper*: Set a custom wallpaper for the IDE frame
- *Material Design components*: Enable/Disable Material Design components (Buttons, Progress Bars...)
- *Material Icons*: Enable/Disable the Material Theme icons to the original ones
- *Material Theme*: Enable/Disable the material theme styles
- *Project View Decorators*: Enable/Disable the Project View Decorators. See the above section about separators.
* *Theme in Statusbar*: Add an indicator for the currently selected theme in the statusbar.
- *Transparent Scrollbars*: Enable/Disable transparency in scrollbars

#### Settings File

Your current configuration is stored inside the IDE settings' directory under the filename `material_theme.xml`. If for some
reason there are issues with the plugin or you don't manage to reset to a stable state, just delete this file and
restart the IDE.

See
[IDE Settings](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs)
for more information.


{%comment%}
## Material Custom Theme Settings

## Troubleshooting
{%endcomment%}
