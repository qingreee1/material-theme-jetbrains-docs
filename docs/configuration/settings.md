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

{% include carbonads.html %}

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

Or, since version 2.4.0, from the Settings at `Settings -> Appearance -> Material Theme`.

{% include figure.html content="/screens/switchSettings.png" caption="Switcher in Settings" %}

**Note that this dropdown won't show the [External Themes]({{site.baseurl}}/docs/development/external-themes).**

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

#### External Themes

The plugin also defines an endpoint allowing plugin developers to create and bundle their own custom themes, just like Color Schemes, Keymaps, Coding Styles and so on. To learn more about _External Themes_ or how to create your own plugin, head to the [External Themes section]({{site.baseurl}}/docs/development/external-themes).

Once the external theme has been downloaded and installed, it should appear at the bottom of the Switcher

{% include figure.html content="/screens/external.png" caption="External Themes" %}

{% include figure.html content="/tutorial/externalList.png" caption="External Themes List" %}

#### Custom Theme

Finally there are the **Custom Themes**, which is the placeholder for the Custom Theme Settings, allowing you set
your own theme colors.

There are two options available, *Custom Theme* for themes based off the *Darcula* LaF, and *Light Custom Theme* based off the *IntelliJ* LaF.

See [Custom Themes]({{site.baseurl}}/docs/configuration/custom-themes) for more information.

----------
## Material Theme Settings

### Settings Page

You can configure many parts of the plugin features from the **plugin settings**. They are accessible within `Settings
-> Appearance -> Material Theme`.

{% include figure.html content="/screens/settings.png" caption="Material Theme Settings v1" %}

Since version 2.4.0, the settings page has been remade to prevent overwhelming the user from the abundance of customization options.

Now settings are differentiated between basic and advanced settings, they are grouped within _tabs_, and a *Reset Settings* button has been added for easy factory resetting.

{% include figure.html content="/screens/settingsV2.png" caption="Material Theme Settings V2" %}

#### Summary

Quick summary of all settings:

**Main Settings**:
- *Selected Theme*: Select a _Material Theme skin_ (will not display External Themes)
- *Contrast Mode*: Add contrast to specific panels and components.
- *Custom Accent Color*: Set a custom accent color.
- *Override Accent Color*: Change the accent color accordingly to the selected theme
- *Configure Custom Theme Colors*: Open the settings page for the _Custom Theme Colors_.

**Tab Settings**:
- *Active Tab Highlight Color*: Customize the active tab's indicator color
- *Thickness*: Set the active tab's indicator thickness (works also for the Project View selected row)
- *Uppercase Bold Tabs*: Set the Editor Tabs in Bold Upper Case
- *Tabs Height*: Set a custom height for tabs (between 25 and 60 pixels)
- *Tab Color Opacity*: Adjust the opacity of the custom colors in tabs (for File Colors)

**Panel Settings**:
- *Compact Status Bar*: Reduce the height of the status bar (this is the default height)
- *Compact Table Cells*: Reduce the height of table headers and table cells
- *Compact Dropdown Lists*: Reduce the size of the dropdowns

**Icons Settings**:
- *Material UI Icons*: Enable/Disable the Material UI icons
- *Hide Icons*: Hide file icons (depends on the Material Icons option)
- *Monochrome Icons*: Filter the IDE icons with a shade of the current theme's primary color
- *File Icons*: Enable/Disable the Custom File Icons
- *Folder decorators*: Decorate special folders with custom icons.

**Project View Settings**:
- *Custom Sidebar Height*: Set a custom line height in Project View (min: 18, max: 30)
- *Custom Tree Indent*: Fine tune indent of Project View items (both left and right indent)
- *Selected Indicator Style*: Change the style of the indicator of the selected item in trees
  - *Border*: Add a small border to the left
  - *Dot*: Add a dot on the selected item
  - *None*: Remove the indicator
- *Thickness*: Control the thickness of the indicator
- *Arrows Style*: Change the style of the arrows in trees. Choices:
  - *Material*: Chevron arrows
  - *Darcula*: Triangle arrows
  - *Plus-Minus*: Plus and Minus symbols
  - *None*: Hide the arrows
- *Custom Directories Style*: Assign a specific style to directories. Can be configured from the *Material File Colors* settings page.
- *Font Size*: Fine tune the font size of the project trees (require restart)

**Component Settings**:
- *Uppercase buttons*: Set the buttons' texts to uppercase
- *Transparent Scrollbars*: Enable/Disable transparency in scrollbars (require restart)
- *Accent Scrollbars*: Set the color of the scrollbars to the accent color (require restart)
- *High Contrast*: Set a darker color of the backgrounds and lighter color of texts for more contrast

**Features Settings**:
- *Material Fonts*: Enable/Disable Material fonts (Roboto) (require restart)
- *Material Theme*: Enable/Disable the Material Theme Colors.
- *Material Design components*: Enable/Disable [Material Design components]({{site.baseurl}}/docs/reference/components) (require restart)
- *Material File Status Colors*: Enable/Disable the [Material File Status Colors]({{site.baseurl}}/docs/configuration/file-status-colors)

**Other Tweaks**:
- *Project View Decorators*: Enable/Disable the Project View Decorators.
* *Theme in Statusbar*: Show the currently selected theme in the statusbar.
- *Themed Title Bar*: Colorize the title bar of the whole OS with the current theme's background color (Mac-Windows only)

#### Settings shortcuts

You can also quickly toggle some settings by using the _Search Everywhere_ or the _Command Panel_ functionality of the IDE. Simply press `Shift-Shift` and type `Material Theme` to get the list of avialable toggles.

Same goes for the `Command Panel` (<kbd>Ctrl-Shift-A/Cmd-Shift-A</kbd>).

{% include figure.html content="/screens/cmdpanel.png" caption="Command Panel" %}

You can also use the `Keymap Editor` to assign your own custom shortcuts to these toggles.

#### Settings File

Your current configuration is stored inside the IDE settings' directory under the filename `material_theme.xml`. If for some reason there are issues with the plugin or you don't manage to reset to a stable state, just delete this file and restart the IDE.

See [IDE Settings](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs) for more information.


----------
## Material Custom Theme

Aside from the bundled themes, you also have the ability to set up your own Material Theme. You can do so by selecting `Custom Theme` in the theme list, then customize the theme colors in the settings page at `Settings -> Appearance -> Material Custom Theme Settings`.

{% include figure.html content="/screens/customsettings.png" caption="Material Custom Theme Settings" %}

Once you set these colors, simply select the `Custom Theme` in the _Material Theme Chooser_ and see your colors take effect.

See [Custom Theme]({{site.baseurl}}/docs/configuration/custom-themes) for more details.

-----
## Advanced Configuration

Here's a list of the keys used in the configuration file (`material_theme.xml`) with their default values. If a key isn't in your file, it means that it uses the default value.

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
  <!-- Tab Opacity -->
  <option name="tabOpacity" value="50" />

  <!-- Contrast mode enabled -->
  <option name="isContrastMode" value="false" />
  <!-- Monochrome icons -->
  <option name="monochromeIcons" value="false" />
 <!-- File icons hidden -->
  <option name="hideFileIcons" value="false" />
  <!-- Custom tree indent enabled -->
  <option name="isCustomTreeIndentEnabled" value="false" />
  <!-- Right tree indent  -->
  <option name="rightTreeIndent" value="6" />
  <!-- Left tree indent  -->  
  <option name="leftTreeIndent" value="6" />

  <!-- Custom sidebar line height enabled -->
  <option name="compactSidebar" value="false" />
  <!-- Sidebar line height if enabled -->
  <option name="customSidebarHeight" value="28" />

  <!-- Styled folders enabled -->
  <option name="isStyledDirectories" value="true" />
  <!-- Compact status bar enabled -->
  <option name="isCompactStatusBar" value="false" />
  <!-- Compact table cells enabled -->
  <option name="isCompactTableCells" value="false" />
  <!-- Compact dropdowns enabled -->
  <option name="isCompactDropdowns" value="false" />
  <!-- Project View Font Size -->
  <option name="treeFontSize" value="12" />

  <!-- Accent color -->
  <option name="accentColor" value="80cbc4" />

  <!-- Indicator Style -->
  <option name="indicatorStyle" value="Line" />
  <!-- Indicator Thickness -->
  <option name="indicatorThickness" value="2" />

  <!-- Arrows style -->
  <option name="arrowsStyle" value="Material" />

  <!-- Uppercase buttons -->
  <option name="uppercaseButtons" value="true" />
   <!-- High Contrast -->
  <option name="highContrast" value="true" />
  <!-- Material components enabled -->
  <option name="isMaterialDesign" value="true" />
  <!-- Material UI icons enabled -->
  <option name="useMaterialIcons" value="true" />
  <!-- Material fonts enabled -->
  <option name="useMaterialFonts" value="true" />
  <!-- Project view decorators enabled-->
  <option name="useProjectViewDecorators" value="true" />
   <!-- File Icons enabled-->
  <option name="fileIcons" value="true" />
  <!-- Decorated folders enabled-->
  <option name="decoratedFolders" value="true" />
  <!-- File Status Colors enabled-->
  <option name="fileStatusColorsEnabled" value="false" />

  <!-- Material Theme styles enabled -->
  <option name="isMaterialTheme" value="true" />

  <!-- Status bar indicator -->
  <option name="statusBarTheme" value="true" />
  
  <!-- Transparent scrollbars -->
  <option name="themedScrollbars" value="false" />
  <!-- Accent scrollbars -->
  <option name="accentScrollbars" value="true" />
    
  <!-- Themed Title Bar -->
  <option name="darkTitleBar" value="false" />
  
  <!-- Override accent color enabled -->
  <option name="overrideAccent" value="false" />
  
  <!-- Internal settings (do not modify) -->
  <!-- Current plugin version -->
  <option name="version" value="2.4.0" />
  <!-- Internal ID for Analytics -->
  <option name="userId" value="" />
  <!-- Whether or not to send data analytics -->
  <option name="allowDataCollection" value="false" />
  <!-- Last selected tab in the settings -->
  <option name="settingsSelectedTab" value="0" />
  <!-- Whether to show the wizard at start -->
  <option name="isWizardShown" value="true" />
        
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
image` and then manually remove the image, or go into `Settings -> Appearance -> Background Image`.

**Q**: The title bar is sweet! But now all my applications, not only JetBrains IDE, have the colored title bar! Plus, when I close the IDE/disable/uninstall the plugin, the title bar stays the same! Help! (Note: I'm on Windows)

**A**: The Themed Title Bar option is indeed changing the title bar accent color OS-wide, therefore it sticks even when the IDE is closed/uninstalled. If you want to change it back, follow the instructions at https://www.howtogeek.com/225893/how-to-personalize-windows-10s-appearance/

**Q**: The dark title bar on Mac sticks as well! **Update: since version 2.5.0, it doesn't!**

**A**: As for the Mac Title bar, it is triggered using the IDE Registry. Just open the Command Palette (Cmd-Shift-A) and type `Registry`. Then you will see at the top the edited values. Simply press Revert to those you don't want.
**Update 2.5.0:** The Dark Title Bar option is now moved inside the Appearance panel of the IDE, named _Dark Window Headers_ (and currently contains the same problems as above). However, a new option `Themed Title Bar` have taken its place in the Material Theme Settings and allows to completely theme the title bar. However it also overrides the _Dark Window Headers_ setting.

**Q**: The uppercase buttons are nice, but sometimes the text overflows the button

**A**: This is because the buttons were designed primarily to be regular cased, therefore changing the case might extend the length of the text and therefore overflows outside of the button. This is quite uncommon though so if it bothers you that much, simply disable the feature.

**Q**: What is that analytics option? What data are collected?

**A**: This is an option to allow sending data to Material Theme servers about users' configuration, usage and trends. These analytics will allow us to see which features are most used or least used, in order to prioritize development of features, or maybe notify users about specific features, to provide a better satisfaction. These data are completely anonymous, and are not shared/sold to any third parties. If you want to stop sending data, simply turn off the option in the material_theme.xml.

**Q**: The wizard idea was great! But I've made an error and the wizard won't show up anymore!

**A**: The wizard will show only once and only when you don't have the `isWizardShown` option set to true in the config file. But you can reopen it by simply clicking on the action from the Material Theme Toolbar, in the Features menu.
