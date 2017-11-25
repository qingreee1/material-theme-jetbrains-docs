---
layout: docs
title: Component Settings
description: Enable or disable Material Theme features
group: configuration
toc: true

previous:
  url: '/docs/configuration/panel-settings'
  title: Panel Settings
next:
  url: '/docs/configuration/status-file-colors
  title: Status File Colors

---

Here you can turn on and off specific features of the Material Theme for even further customization.
{:class='title'}

## Custom Wallpaper

### IDE Background Image

This feature is **not** a feature offered by the plugin but rather makes use of a rather unknown feature of the IDE which allows you to set a ++background image++ to the IDE frame.

To invoke it, open the command panel (`Cmd-Shift-A`/`Ctrl-Shift-A`) and type *Set Background image*. Pretty much hard to find isn't it?

This option makes it more accessible from inside the Material Theme Settings and sets up a default background image related to Material Design.

{% include figure.html content="/screens/customWallpaper.png" caption="Custom Wallpaper" %}

### Custom Wallpaper Settings

With this setting you can set up a custom background image from your computer or from an online location. To select a local image from your computer, click on the `...` button and select a file. Online location requires an internet connection to display your selected image.

You can also control the opacity, layout and position of the background image by adding comma separated values at the end of the path. The format should be like this: `<image_path>,<opacity>,<layout>,<position>`, each value being optional.

- Opacity values vary from `0` (transparent) to `100` (opaque).
- Layout is one of the values: `plain`, `scale` or `tile`
- Position is one of the values: `center`, `top-left`, `top-center`, `top-right`, `bottom-left`, `bottom-center`, `bottom-right`

If you wish to restore the default image preselected by the plugin, simply click on the `Restore` button.

**Note**: Because the original background image feature cannot use bundled resources, the default image is loaded from the github repository and not from the plugin itself, which means that an active connection is required and that if you have a firewall that prevents you from doing external requests, the image won't be loaded.
{:class='card-panel'}


### Custom Wallpaper Known issues

While this feature is very neat, the fact that it uses and changes the original IDE background image feature creates some problems when using the two features together. Therefore:
- If you plan on using the IDE feature, turn off the option in the Material Theme settings. This will prevent issues such as the background being changed at start.
- If you don't want any background at all, delete the value in the setting while leaving the checkbox checked and click Apply.
- If you disable or uninstall the plugin, it will not revert back to no wallpaper since the wallpaper is set at the start when the plugin is activated. You will have to undo it manually by using the IDE feature.

Apart from these, the option should simplify setting and changing the IDE's wallpaper easier than the original setting. If in the future they improve this function and provide an easier way to do it than currently, there's a probability that this option will be removed altogether.

-----

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
