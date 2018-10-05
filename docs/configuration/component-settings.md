---
layout: docs
title: Component Settings
description: Further customize the IDE components
group: configuration
comments: true
toc: true

previous:
  url: '/docs/configuration/project-view-settings'
  title: Project View Settings
next:
  url: '/docs/configuration/features-settings'
  title: Features Settings
---

Further customization of the IDEs components
{:class='title'}

{% include carbonads.html %}

### Uppercase buttons

This setting sets the text of the buttons to be uppercase, just like the Material Design buttons. This is optimal for a full-fledged Material Design Experience.

{% include figure.html content="/screens/uppercaseButtons.png" caption="Uppercase Buttons" %}

**Note**: Because the UI was originally intended to be used with regular cased buttons, some buttons with longer text will have their text overflowing outside of the button. Since it's not possible to control the size of such buttons, there is no possibility to fix this. However, such cases are quite rare, but if it's absolutely bothers you, you can still disable this feature via this option.
{:class='card-panel warn'}

-----
### Transparent Scrollbars and Accent Scrollbars

These options control the appearance of the scrollbars. Note: This feature works completely on Windows and Linux, but on Mac it is only working for non-native scrollbars (e.g. scrollbars that appear only while scrolling).

*Transparent scrollbars* will add transparency to the scrollbars and set it as the same color as the current theme's background color. This is adding _50% opacity_ and there is no way to change it.

*Accent scrollbars* will replace the scrollbar color with the _current accent color_.

{% include figure.html content="/screens/scrollbars.png" caption="Accent Scrollbars" %}

**Note**: These options are mutually exclusive, and _Accent Scrollbars_ take precedence over _Transparent Scrollbars_.

**Note 2**: Accent colors are set at the start of the application. If you change the accent color, you will need a full restart to see it reflected on the scrollbars. Same for the _Transparent Scrollbars_, if you switch themes you will need a full restart to see it taking the new theme's color.

---
### Tabs Shadow

This option enables/disables the shadow under the tabs.

{% include figure.html content="/screens/tabsShadow.png" caption="Tabs Shadow" %}

-----
### FAQ/Troubleshooting

- Scrollbar settings actually mess up the Registry. Therefore, they can persist even when the plugin is off/disabled (though they shouldn't). In that case, you can reset the default settings by opening the Registry (`Cmd-Shift-A > Registry`) then resetting all custom values in there.
- Scrollbar settings don't work well with HiDPI (yet). For a better experience, please disable these options if you are on such environments until it is fixed.
