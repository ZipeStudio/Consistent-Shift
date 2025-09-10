<table style="width: 100%; border-collapse: collapse;">
  <tr>
    <td style="width: 124px; vertical-align: top; text-align: center;">
      <div style="display: flex; justify-content: center; align-items: center;">
        <img src="https://raw.githubusercontent.com/ZipeStudio/Vault/refs/heads/main/design/mods/main/zipestudio.png" title="It's me">
      </div>
    </td>
    <td style="vertical-align: top;">
      <div style="display: flex; flex-direction: column;">
        <div style="display: flex;">
          <a href="https://github.com/ZipeStudio/Consistent-Shift">
            <img src="https://raw.githubusercontent.com/ZipeStudio/Vault/refs/heads/main/design/mods/main/github.png" title="Github page">
          </a>
          <a href="https://modrinth.com/mod/consistent-shift">
            <img src="https://raw.githubusercontent.com/ZipeStudio/Vault/refs/heads/main/design/mods/main/modrinth.png" title="Modrinth page">
          </a>
          <a href="https://www.curseforge.com/minecraft/mc-mods/consistent-shift">
            <img src="https://raw.githubusercontent.com/ZipeStudio/Vault/refs/heads/main/design/mods/main/curseforge.png" title="CurseForge page">
          </a>
        </div>
        <div style="display: flex;">
          <a href="https://discord.com/invite/XmGF7rkkuY">
            <img src="https://raw.githubusercontent.com/ZipeStudio/Vault/refs/heads/main/design/mods/main/discord.png" title="Discord account">
          </a>
          <a href="https://t.me/zipeleaf">
            <img src="https://raw.githubusercontent.com/ZipeStudio/Vault/refs/heads/main/design/mods/main/telegram.png" title="Telegram channel">
          </a>
          <a href="https://ko-fi.com/zipestudio/tip">
            <img src="https://raw.githubusercontent.com/ZipeStudio/Vault/refs/heads/main/design/mods/main/support.png" title="Support me (thx)">
          </a>
        </div>
      </div>
    </td>
  </tr>
</table>

# 💬 Description
> **Makes Shift+Click behavior consistent: items from any container are moved left to right, just like in the player's inventory.**
> Works for **chests, furnaces, crafting tables, and all containers**.

---

## 🔹 GIF Showcase
<div align="center">
  <img src="https://raw.githubusercontent.com/ZipeStudio/Consistent-Shift/refs/heads/master/img/showcase.gif" width="512px" alt="mod showcase"/>
</div>

---

## ⚠️ Known Issue
Currently, when using **Ctrl + double-click (Pickup All)** in a container, vanilla logic is still applied.  
Instead of following the mod’s rule (**left → right, starting with hotbar**), items are placed **right → left** across the inventory.

<div align="center">
  <img src="https://raw.githubusercontent.com/ZipeStudio/Consistent-Shift/refs/heads/master/img/known_issue.gif" width="512px" alt="mod showcase"/>
</div>

Here’s the difference:
```diff
Expected (Consistent Shift):
[Hotbar 1] → [Hotbar 2] → ... → [Hotbar 9] → [Main Inventory Left → Right]

Vanilla (current bug):
[Hotbar 9] → [Hotbar 8] → ... → [Hotbar 1] → [Main Inv Right → Left]
```

## 🤝 Contributing
If you know how to fix the **Pickup All bug** (or have ideas for improvements), feel free to **open an issue or a pull request on the [GitHub project page](https://github.com/ZipeStudio/Consistent-Shift)**.  
Community contributions are very welcome!