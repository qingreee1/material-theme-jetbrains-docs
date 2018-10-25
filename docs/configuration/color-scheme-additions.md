---
layout: docs
title: Additions
description: Color Scheme Additions
group: configuration
comments: true
toc: true

previous:
  url: '/docs/configuration/accents'
  title: Accents
next:
  url: '/docs/configuration/custom-themes'
  title: Custom Themes

---

Augment languages highlighting capabilities.
{:class='title'}

{% include carbonads.html %}

## Intro

This feature is much more oriented development than the others as it provides new features for the Color Schemes rather than the UI. Basically it adds more customization to the Color Schemes by providing more syntax highlighting options than the original bundled ones, thus enhancing visual grepping.

Originally introduced by the [Night Owl Theme](https://github.com/xdrop/night-owl-jetbrains), the plugin augments its features by giving more highlighting options.

{% include figure.html content="/screens/jsExtensions.png" caption="JavaScript Additions" %}


## Additions

Currently the only supported languages are **JavaScript** and **TypeScript**, but more will come with further updates.

### JavaScript

The following keywords are customizable:
- `this`, `super`
- `import`, `export`, `module`, `from`
- `null`, `undefined`
- `debugger`
- `var`, `let`, `const`
- `function`

### TypeScript

TypeScript augments JavaScript additions with the following keywords:
- `private`, `public`, `protected`

---

## FAQ

**Q**: The feature doesn't work! My var/this/debugger keywords are still the same color as the others! By the way I'm using Darcula/IntelliJ/My custom scheme.

**A**: Since these are *Additions*, of course other schemes are unaware of these new entries and will revert back to the default keyword color. However color scheme designers can provide their own color definitions for the new entries if they choose to *Export scheme* after having selected their colors, and it will work for people having the plugin installed.

**Q**: What about Night Owl's own additions? Won't it clash with Material Theme's?

**A**: The _Material Theme Additions_ are using the same keys as the _Night Owl Additions_, while adding new ones. Therefore assigning a color in the plugin's page will work for NightOwl and vice-versa.



