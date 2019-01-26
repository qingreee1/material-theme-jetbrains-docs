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

**Note 3**: Since 2019.1 versions, the editor scrollbar color is now set by the **Color Scheme** instead of by the **Theme**. This makes sense as the color scheme is not necessarily following the theme and might be off with the theme.
There is no option to configure this at the moment but it will soon be available inside the _Color Scheme General Settings_.

---
### Tabs Shadow

This option enables/disables the shadow under the tabs.

{% include figure.html content="/screens/tabShadow.png" caption="Tabs Shadow" %}
