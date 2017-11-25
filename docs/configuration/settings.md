---
layout: docs
title: Settings
description: Learn how to configure the plugin
group: configuration
toc: true
comments: true

previous:
  url: '/docs/getting-started'
  title: Getting Started
next:
  url: '/docs/configuration/color-schemes'
  title: Color Schemes
---

The Material Theme plugin comes with a bunch of features tailored for an optimal experience. However, it is also highly
configurable to allow each and everyone to customize it to their hearts content.
{:class='title'}

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

Finally there is a **Custom Theme** option, which is the placeholder for the Custom Theme Settings, allowing you set
your own theme colors.

See [Custom Themes](custom-themes.md) for more information.

----------
## Material Theme Settings

You can configure many parts of the plugin features from the **plugin settings**. They are accessible within `Settings
-> Appearance -> Material Theme`.

{% include figure.html content="/screens/settings.png" caption="Material Theme Settings" %}

#### Summary

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
- *Accent Scrollbars*: Set the color of the scrollbars to the accent color

#### Settings shortcuts

You can also quickly toggle some settings by using the `Search Everywhere` or the functionality of the IDE. Simply press
`Shift-Shift` and type `Material Theme` to get the list of avialable toggles.

Same goes for the `Command Panel` (<kbd>Ctrl-Shift-A/Cmd-Shift-A</kbd>).

{% include figure.html content="/screens/cmdpanel.png" caption="Command Panel" %}

You can also use the `Keymap Editor` to assign your own custom shortcuts to these toggles.

#### Settings File

Your current configuration is stored inside the IDE settings' directory under the filename `material_theme.xml`. If for some
reason there are issues with the plugin or you don't manage to reset to a stable state, just delete this file and
restart the IDE.

See
[IDE Settings](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs)
for more information.


----------
## Material Custom Theme

Aside from the bundled themes, you also have the ability to set up your own Material Theme. You can do so by selecting
`Custom Theme` in the theme list, then customize the theme colors in the settings page at `Settings -> Appearance ->
Material Custom Theme Settings`.

{% include figure.html content="/screens/customsettings.png" caption="Material Custom Theme Settings" %}

Once you set these colors, simply select the `Custom Theme` in the _Material Theme Chooser_ and see your colors take effect.

Note: Due to how themes are implemented in the SDK, this feature does not work completely well. Therefore, in order to
see your custom theme take effect, you will probably need to restart the IDE to see the changes.

See [Custom Theme](custom-themes.md) for more details.

-----
## Advanced Configuration

Here's a list of the keys used in the configuration file (`material_theme.xml`) with their default values. If a key isn't in
your file, it means that it uses the default value.

```xml
<component>
  <!-- Tab highlight color -->
  <option name="highlightColor" value="80cbc4" />
  <!-- Tab highlight color enabled -->
  <option name="highlightColorEnabled" value="false" />
  <!-- Tab highlight thickness -->
  <option name="highlightThickness" value="2" />

  <!-- Tab height -->
  <option name="tabsHeight" value="50" />
  <!-- Bold and uppercase tabs enabled -->
  <option name="upperCaseTabs" value="true" />

  <!-- Contrast mode enabled -->
  <option name="isContrastMode" value="false" />
  <!-- File icons hidden -->
  <option name="hideFileIcons" value="false" />
  <!-- Custom tree indent enabled -->
  <option name="isCustomTreeIndentEnabled" value="false" />
  <!-- Custom tree indent value -->
  <option name="customTreeIndent" value="4" />

  <!-- Custom sidebar line height enabled -->
  <option name="compactSidebar" value="false" />
  <!-- Sidebar line height if enabled -->
  <option name="customSidebarHeight" value="28" />

  <!-- Bold folders (not tabs) enabled -->
  <option name="isBoldTabs" value="true" />
  <!-- Compact status bar enabled -->
  <option name="isCompactStatusBar" value="false" />

  <!-- Accent color -->
  <option name="accentColor" value="80cbc4" />

  <!-- Custom background -->
  <option name="defaultBackground" value="https://raw.githubusercontent.com/mallowigi/material-theme-jetbrains-eap/master/src/main/resources/themes/wall.jpg,60" /> 
  <!-- Material components enabled -->
  <option name="isMaterialDesign" value="true" />
  <!-- Material and File icons enabled -->
  <option name="useMaterialIcons" value="true" />
  <!-- Project view decorators enabled-->
  <option name="useProjectViewDecorators" value="true" />
  <!-- Material Theme styles enabled -->
  <option name="isMaterialTheme" value="true" />

  <!-- Status bar indicator -->
   <option name="statusBarTheme" value="true" />
   <!-- Transparent scrollbars -->
    <option name="themedScrollbars" value="false" />
    <!-- Accent scrollbars -->
    <option name="accentScrollbars" value="true" />
</component>

```

## Frequently Asked Questions

**Q**: I've installed the Material Theme, but the editor still shows me the `Darcula` theme?

**A**: The Theme Switcher is only replacing the theme of the whole IDE, not the editor itself. You can therefore have a
separate theme for the IDE and the Editor, for instance _Palenight_ and _Darker_ or even a custom Color Scheme.

**Q**: I've installed the plugin, yet the _Look and Feel_ section still shows me only `IntelliJ` and `Darcula`?

**A**: That's because the plugin DIDN'T install a new Look And Feel, instead it is **using** the original IntelliJ/Darcula
LaF and overwrites its colors/fonts/components. If you switch to another LaF, you will need to reselect back a Material
Theme using the _Theme Switcher_ in order to apply its colors.

**Q**: I've updated the IDE/plugin to a new version and now I get an error about the plugin failing to initialize?

**A**: It could come from multiple issues, but it could simply be a problem with the settings not being compatible to the
new version. In that case, simply make a copy of the settings file, then delete the original file, and restart the IDE.

If the issue is gone, simply go back to the settings and go back to your previous configuration manually. If the issue
persists, please report it in the Issues Section.

**Q**: I've removed the plugin, and still the background image persists!

**A**: This is an issue hard to resolve, because the *Custom Wallpaper* function is using the `Set Background image`
function from the IDE behind the curtains. Therefore, removing the plugin might not remove the set image completely. If
that occurs, you can remove the image by opening the Command Panel (Cmd-Shift-A/Ctrl-Shift-A) and type `Set Background
image` and then manually remove the image.
