# StarterKitPlugin

A simple, lightweight Minecraft Spigot/Paper plugin that gives new players a configurable starter kit **only once** on their first join.

## Features
- One-time starter kit on first join
- Easy configuration via `config.yml`
- `/starterkit` command to manually give the kit
- Permission support: `starterkit.give`
- Lightweight with no external dependencies

## Installation
1. Download the latest release JAR from the Releases tab (or build it yourself)
2. Place it in your server's `plugins/` folder
3. Restart the server
4. Customize `plugins/StarterKitPlugin/config.yml`
5. Use `/starterkit reload` or restart to apply changes

## Commands
- `/starterkit` - Give kit to yourself
- `/starterkit <player>` - Give kit to another player (requires `starterkit.give`)

## Permissions
- `starterkit.give` - Allows using the command on other players

## Building from Source
```bash
mvn clean package
```
The compiled JAR will be in the `target/` folder.

## Configuration
Edit `src/main/resources/config.yml` (or the one in `plugins/StarterKitPlugin/` after first run):

```yaml
starter-kit:
  - WOODEN_SWORD:1
  - WOODEN_PICKAXE:1
  - BREAD:32
  # Add more items here
```

## License
MIT License - Feel free to use and modify!

---

Made with ❤️ for the community.