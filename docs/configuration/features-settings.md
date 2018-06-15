---
layout: docs
title: Component Settings
description: Enable or disable Material Theme features
group: configuration
comments: true
toc: true

previous:
  url: '/docs/configuration/component-settings'
  title: Component Settings
next:
  url: '/docs/configuration/other-tweaks-settings'
  title: Other Tweaks
---

Here you can turn on and off specific features of the Material Theme for even further customization.
{:class='title'}

{% include carbonads.html %}

## Enabling and Disabling features

The following options allow you to enable and disable features provided by the Material Theme. This gives you the ability to specifically select features such as theme colors or icons while disabling the rest if you don't like it. For instance, if you like the icons but don't like the themes and components, just leave the icons on and disable the rest. If on the other hand you like everything but prefer to use the default icons, just disable the icons.
{:class='title'}

### Material Design Components

This toggles the theming of components inside the IDE such as *buttons*, *text fields* and *checkboxes*. This is nice if you like the theme colors and icons but don't like the Material Design flat components, padded rows and borderless boxes.

Specifically this option disables:
- Flat Buttons
- Borderless input, number, password and search boxes
- Borderless dropdowns
- Accent progress bars
- Trees' selected item indicator
- Trees' opened and closed folders chevrons (>)
- Padded Table Headers and Table Cells
- Padded List Items
- Borderless status bar
- Material Design progress circular loader/spinner
- Material Design Checkboxes, Radio Buttons and Sliders
- Search Everywhere's Switches
- Material Design Navigation Bar

But while these components are disabled, other features such as _Accent Colors_ and _Contrast Mode_ are still in action, meaning that you can have Darcula checkboxes and buttons with your accent color, or the contents of inputs being in contrasted color, for instance.

{% include figure.html content="/screens/componentsEnabled.png" caption="Material Components Enabled" %}

{% include figure.html content="/screens/componentsDisabled.png" caption="Material Components Disabled" %}

What it doesn't disable however, albeit Material Design components, are:
- Dialog boxes padded titles
- Toggled buttons indicators
- Run Configurations dropdown

This is because currently these components are not as themeable as the other ones, and theming occur at the start of the application, without having access to the user settings. If the API changes and they become easier to theme, they will be listed in the first list, but for now there is no way to disable theming of these components othen than removing the plugin.

**Note**: Because this option completely replaces the components UI, a complete restart of the application is needed in order to see them in action.

**Note 2**: Some of the features depend on this setting to work, therefore disabling this will result to disabling those features as well. These are:
- _Padded Statusbar_: The statusbar will revert to its default, compact design.

----

### Material Fonts

This setting switches the default font used in the editor with the usual font used in Material Design, [Roboto](https://fonts.google.com/specimen/Roboto).

This setting changes the font you see in *Settings -> Appearance and Behavior -> Appearance*. When unchecked, it will restore the original default font, e.g. `Tahoma` in Windows, `SF_Text` in Mac and `Lucida Sans` in Linux. When checked, it will replace it with `Roboto` (only if the font is already installed in the machine though).

**Note**: This setting is requiring that **default fonts are not overriden**, e.g. that the setting at `Appearance and Behavior -> Appearance -> Override default font` is unchecked, in which case it will use the font set there.
{:class='card-panel'}

**Note 2**: This setting will replace the font with Roboto only if that font is already installed in the machine. If not, you can download it here: https://material.io/guidelines/resources/roboto-noto-fonts.html
{:class='card-panel'}

**Note 3**: If you want to use Roboto and want to cancel the "bigger fonts" option, just disable the setting and select Roboto in the `Override default font` option in Appearance settings.
{:class='card-panel'}

----
### Material Theme

This option simply disables the Material Theme Styling, e.g. leave components and icons intact but revert to the original Darcula/IntelliJ Look And Feel.

{% include figure.html content="/screens/themeDisabled.png" caption="Material Theme Disabled" %}

**Note**: The IDE will revert back to the most appropriated Look And Feel, e.g. if you were on Material Oceanic or Darker, you will revert back to **Darcula**. If you were on Material Lighter, you will be reverted to **IntelliJ**.

**Note 2**: Some of the features depend on this setting to work, therefore disabling this will result to disabling those features as well. These are:
- _Themes and Custom Themes_: You won't be able to switch themes (obviously)
- _Contrast Mode_: There is no contrast mode for native themes
- _Custom Sidebar Height_: You will be reverted to Darcula/IntelliJ native sidebar height
- _Custom Tree Indent_: You will be reverted to Darcula/IntelliJ native indent
- _Bold Directories_: There is no way to bold directories in native themes
- Other components will also revert back to their default values, such as checkboxes signs, tabbed pane insets, etc.

----
### Material File Status Colors

This option enables Material File Status Colors, allowing customization of the file status colors from Color Schemes.

Check [Material File Status Colors]({{site.baseurl}}/docs/configuration/file-status-colors) for more information.

-----
## FAQ/Troubleshooting

**Q**: I've set "Material Fonts" but now my texts are all scrambled!

**A**: This is a rare bug that occurs and honestly I don't know why it happens. It could be because the JDK caches fonts or because there are conflicts between Roboto fonts, or that you have a bad version of Roboto. Try to reinstall the Roboto font and restart the computer. If it still doesn't work, just disable the option or select another font in the settings.
