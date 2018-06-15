---
layout: docs
title: Troubleshooting
group: troubleshooting
toc: true
comments: true

previous:
  url: '/docs/acknowledgements'
  title: Acknowledgements

---

Here you can find all the Troubleshooting FAQs.
{:class='title'}

## Settings

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

**Q**: The title bar is sweet! But now all my applications, not only Jetbrains IDE, have the colored title bar! Plus, when I close the IDE/disable/uninstall the plugin, the title bar stays the same! Help! (Note: I'm on Windows)

**A**: The Themed Title Bar option is indeed changing the title bar accent color OS-wide, therefore it sticks even when the IDE is closed/uninstalled. If you want to change it back, follow the instructions at https://www.howtogeek.com/225893/how-to-personalize-windows-10s-appearance/

**Q**: The dark title bar on Mac sticks as well!

**A**: As for the Mac Title bar, it is triggered using the IDE Registry. Just open the Command Palette (Cmd-Shift-A) and type `Registry`. Then you will see at the top the edited values. Simply press Revert to those you don't want.

**Q**: The uppercase buttons are nice, but sometimes the text overflows the button

**A**: This is because the buttons were designed primarily to be regular cased, therefore changing the case might extend the length of the text and therefore overflows outside of the button. This is quite uncommon though so if it bothers you that much, simply disable the feature.

**Q**: What is that analytics option? What data are collected?

**A**: This is an option to allow sending data to Material Theme servers about users' configuration, usage and trends. These analytics will allow us to see which features are most used or least used, in order to prioritize development of features, or maybe notify users about specific features, to provide a better satisfaction. These data are completely anonymous, and are not shared/sold to any third parties. If you want to stop sending data, simply turn off the option in the material_theme.xml.


----
## Main Settings

- Contrast color is also used inside "striped tables", independently of the setting.
- Some resources, such as the scrollbar, will not get the new custom accent color until next restart.

-----
## Tab Settings

**Q**: Is there a way to completely remove the Active Tab Indicator?

**A**: Currently no. Even disabling the feature in the IDE will still display the indicator provided by the plugin. If there is enough demand to add this functionality, we will add it.

**Q**: The Tab Height feature is cool in the editor, but I'd rather leave other tabs untouched.

**A**: Me as well. Unfortunately currently the setting that sets the tab height is shared between all tabs in the SDK, and there is no way to distinguish between them currently. Maybe in the future if Jetbrains agrees to allow more customization natively.

**Q**: Does the active tab highlighter works with the Lighter theme?

**A**: Again, no, because of how the active tab highlight is implemented in the IDEA SDK, it is only working on Darcula based themes, and therefore only on Dark Themes. But maybe I'll try to figure out a fix for this.

**Q**: Why limiting the thickness or the tab height? I want to have 10 in thickness and 100 in tabs!!!

**A**: Because allowing values past these limits would make the UI ugly or worse, crash it. If you have a good reason to want it anyway, you can open an issue on Github with why you would want that. At most, you can still fork the plugin and tweak it to whatever you want.

**Q**: The uppercase tabs feature is so useless! Editor Tabs !== Material Design Tabs!

**A**: While I might agree with this statement, I personally find it a cool feature and it doesn't bother me. It is not allowed by default, so new users will not be startled by it, and if you don't like it you can simply turn it off.

----
## Panel Settings

- Compact Statusbar only works if there are only one open instance of the IDE
- Because the IDE is developed with compact table cells in mind, using "padded table cells" may result of display artifacts in some components. One example is the *Python DataView* (https://github.com/ChrisRM/material-theme-jetbrains/issues/485). If you are using such features a lot, just enable the "Compact Table Cells" option to solve that problem.

----
## Component Settings

- Scrollbar settings actually mess up the Registry. Therefore, they can persist even when the plugin is off/disabled (though they shouldn't). In that case, you can reset the default settings by opening the Registry (`Cmd-Shift-A > Registry`) then resetting all custom values in there.
- Scrollbar settings don't work well with HiDPI (yet). For a better experience, please disable these options if you are on such environments until it is fixed.


----
## Icons Settings

- While the Material Icons replace most icons, some icons, such as icons coming from plugins (including Jetbrains' plugins like Git) would not be replaced.
- Monochrome icons actually messes up some parts of the IDE, such as the SVG Image Viewer. If you need to use it, please disable the option temporarily.

----
## Project View Settings

- Tree views settings work in all tree views, even views such as "Project Structure" or "Remote Host"
- Font Size also replace the Font inside settings.


----
## Feature Settings

**Q**: I've set "Material Fonts" but now my texts are all scrambled!

**A**: This is a rare bug that occurs and honestly I don't know why it happens. It could be because the JDK caches fonts or because there are conflicts between Roboto fonts, or that you have a bad version of Roboto. Try to reinstall the Roboto font and restart the computer. If it still doesn't work, just disable the option or select another font in the settings.


-----
## Other Tweaks

**Q**: **I've disabled the plugin but I still get the themed title bar!**

**A**: This happens because this is not a feature implemented by the plugin, but instead makes use of a feature of the operating system. Therefore even when the IDE is closed the setting will persist. You can change the color of the title bar in Windows by following the instructions at https://www.howtogeek.com/225893/how-to-personalize-windows-10s-appearance/


----
## Custom Themes

**Q**: Can I use more than one custom theme?

**A**: No you can't. If you're confident with your theme, you can fork the project and submit a pull request with your theme as a predefined theme, or submit your theme in the form (not available yet).

**Q**: Where is stored my custom theme colors?

**A**: You can find your custom colors inside the config directory, just like the Material settings.

**Q**: Why do I get a popup asking me for "resetting the theme colors" at start?

**A**: This popup is used to reset the custom theme colors to their default ones and is popping up when switching from a dark theme to a light theme and vice-versa. However sometimes, like for instance when you install the plugin or reset your settings, because it doesn't know which theme you came from, it will ask even though you didn't ask for it. Simply press ok and it will not bother you anymore.

**Q**: I changed the colors but it doesn't look as good as the default themes.

**A**: Creating a theme is not an easy task, and the Material ones are the result of a long thought process about which colors are best suited for an UI. However you can check out other famous Sublime/Atom/Visual Studio themes as an inspiration and start from it.

**Q**: OK I have an idea of a theme, but there is not enough options in the settings for me to make it.

**A**: It's true that those settings are for color palettes of a few colors only, and regroup components of the same purpose under the same color group. If though you'd like to have a different color for checkboxes and radio buttons, or between lists and tables, or set the tree color different as the main background color, etc. - for this the best option would be to fork the project and create a brand new theme.

----
## Excluded File Colors

**Q**: I've switched to Darker Theme and now my Project Tree is very hard to read!

**A**: As I said, simply put the `Darker Excluded Files Color` at the top using the arrows, then save.

**Q**: What happens if I remove/modify the bundled colors?

**A**: If you want to add them back, just run the action again. Not however that existing colors will not be overriden, thus if you find yourself with too many colors just remove the ones you don't need.

**Q**: Is there a way to change the text color?

**A**: The color of the text is controlled by another setting. See [File Status Colors]({{site.baseurl}}/docs/configuration/file-status-colors) for more info.

----
## File Status Colors

Because this feature modifies the original `VCS File Colors` feature, please bear in mind the following issues:
- When switching to other scheme that do not define file status colors, some defaults will be applied which are not the defaults provided by Darcula/IntelliJ, and therefore look bad.
- Uninstalling/Disabling the plugin will not revert back these settings, so you will still keep the last file colors even after restarting.

Thankfully there is an easy fix for that: in the `VCS File Colors` there is a button _Reset Default_ that will revert the value back to the Darcula/IntelliJ default. **Note however that as soon as you change a color scheme the values will change back again.**
