## Overview
This GitHub repository is a mod meant to improve the experience playing Hypixel Skyblock.

## Goal
The goal of this project is to add multiple various features to learn how to make Minecraft mods.

## Features
- A powder mining helper featuring
  - A nearby chest counter that scans
    blocks around the player
  - A Chest ESP to view chests through
    wall (useful when you have a high mole
    level)
  - An auto chest opener (speaks for
    itself)
  - An auto hotbar management that
    automaticlly swaps between your
    pickaxe when mining stone and your
    tool to open chests

## Commands
- /chestConfig
  - set
    - on
    - off
  - width
    - (insert said ESP width here)
  - bl
    - add (blacklist chests so that they
      don't count as nearby chests and the
      ESP doesn't render around it)
    - clear (clears the blacklisted chests
      list)
  - radius
    - (insert the wanted radius for chests
      detection)
  - auto
    - set
      - on
      - off
    - slots
      - pick
        - (insert hotbar value of your
          pick)
      - drill
        - (insert hotbar value of your
          drill)

## Notes
- If /chestConfig doesn't work for you, try using /chestconfig instead.
- Every single feature of this mod is CLIENT SIDE so you don't send any packets. Thus, you don't trigger any Watchdog flags. This means the only way you can get banned is if a staff watches you and bans you (very unlikely).
- This mod is private. If someone gave you acces to it, don't share it without asking me (@_slaymi on discord).

