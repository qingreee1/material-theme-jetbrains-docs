---
layout: docs
title: Component Settings
description: Enable or disable Material Theme features
group: configuration
comments: true
toc: true

previous:
  url: '/docs/configuration/panel-settings'
  title: Panel Settings

---

Here you can turn on and off specific features of the Material Theme for even further customization.
{:class='title'}

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
### Material Theme

This option simply disabled the Material Theme Styling, e.g. leave components and icons intact but revert to the original Darcula/IntelliJ Look And Feel.

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

## Other Component features

These features are additional extras to the plugin, not related to the themes nor Material Design but simply nice addons for the IDE.
{:class = 'title'}

### Project View Decorators

This feature is adding decorations to the Project View to mimic the original Material Sublime Theme decorations.

###### Open and closed directories

When this option is on, files that are _opened_ in the editor and that are _edited_ will have their containing directories have a __hollow directory icon__ (or outline directory) instead of the original icon.

{% include figure.html content="/screens/folderDecorators.png" caption="Opened folder decorators" %}

**Note**: This feature requires *Material Icons* feature to work.

###### Accent colored opened directories

Same as the hollow directory icon, files that are _opened_ and _edited_ will have their containing directories be colored with the selected _accent color_.

-----

### Theme in Status Bar

This setting add a "widget" to the status bar to indicate which theme you are currently on.

{% include figure.html content="/screens/widgetStatusbar.png" caption="Status Bar Widget" %}

-----
### Transparent Scrollbars and Accent Scrollbars

These options control the appearance of the scrollbars. Note: This feature works completely on Windows and Linux, but on Mac it is only working for non-native scrollbars (e.g. scrollbars that appear only while scrolling).

*Transparent scrollbars* will add transparency to the scrollbars and set it as the same color as the current theme's background color. This is adding _50% opacity_ and there is no way to change it.

*Accent scrollbars* will replace the scrollbar color with the _current accent color_.

**Note**: These options are mutually exclusive, and _Accent Scrollbars_ take precedence over _Transparent Scrollbars_.

**Note 2**: Accent colors are set at the start of the application. If you change the accent color, you will need a full restart to see it reflected on the scrollbars. Same for the _Transparent Scrollbars_, if you switch themes you will need a full restart to see it taking the new theme's color.
