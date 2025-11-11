# ShieldStatusFix

A Paper plugin that restores shield status entity packets for legacy clients connecting to modern servers.

Starting 1.21.5 version, changes in packet handling broke shield status mods like [ShieldStatus](https://modrinth.com/mod/shield-statuses) for players on older client versions. This plugin fixes the issue by manually sending the entity status packet (byte 30) when a shield is disabled by an axe hit.

![demo](https://github.com/user-attachments/assets/3426b22e-2236-4ba8-a7ba-3c4521a4f5f8)

## Requirements

- Server: Paper, Purpur or Other forks 1.21.5+
- Dependencies: [PacketEvents](https://modrinth.com/plugin/packetevents) (must be installed)

## Installation


1. Download and install [PacketEvents](https://modrinth.com/plugin/packetevents) on your server
2. Drop ShieldStatusFix into your plugins folder

No configuration needed.

## License

MIT
