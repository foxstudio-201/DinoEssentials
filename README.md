# DinoEssentials

Hệ thống placeholder/prefix/suffix cho chat, tab list, join/leave message.
Hoạt động server-side, config trong `config/dinoessentials-server.toml`.

## Cài đặt

Bỏ jar vào `mods/` của server và client. Khởi động server để sinh config.

## Config

```toml
[general]
enabled = true
chat.enabled = true
tab.enabled = true

[chat]
format = "{prefix}{player}{suffix}&r: {message}"

[tab]
format = "{prefix}{team}{player}{suffix}"

[join]
enabled = true
format = "&a+ &e{player}"

[leave]
enabled = true
format = "&c- &e{player}"

[defaults]
prefix = ""
suffix = ""
```

## Placeholder

| Placeholder | Ý nghĩa | Mod đăng ký |
|------------|---------|-------------|
| `{player}` | Tên người chơi (thô) | DinoEssentials |
| `{username}` | Tên tài khoản | DinoEssentials |
| `{prefix}` | Prefix (config + API) | DinoEssentials |
| `{suffix}` | Suffix (config + API) | DinoEssentials |
| `{message}` | Nội dung tin nhắn | DinoEssentials |
| `{team}` | Tên đội `(TênĐội)` màu | **dinocore** |
| `{level}` | Cấp độ `[lv: X]` màu theo tier | **dinorace** |

## Màu sắc

Dùng & kèm chữ: `&0`-`&9`, `&a`-`&f`, `&l` (đậm), `&o` (nghiêng), `&n` (gạch chân), `&m` (gạch ngang), `&k` (rối), `&r` (reset), `&#RRGGBB` (hex).

## Mod phụ thuộc

- **Bắt buộc:** Forge 1.20.1 (47.x)
- **Không bắt buộc:** dinocore — thêm placeholder `{team}` (tên đội)
- **Không bắt buộc:** dinorace — thêm placeholder `{level}` (cấp độ, màu theo tier)

## API cho mod khác

```java
// Đăng ký prefix
DinoEssentialsAPI.registerPrefix("modid", player -> Component.literal("&7[Prefix] "));

// Đăng ký suffix
DinoEssentialsAPI.registerSuffix("modid", player -> Component.literal(" &7[Suffix]"));

// Đăng ký placeholder tuỳ biến
DinoEssentialsAPI.registerPlaceholder("class", player -> Component.literal("&6[Warrior] "));
```

## Chú ý

- Config là **SERVER** — chỉ server quản trị mới thay đổi được
- DinoEssentials tự động cancel message join/leave mặc định của Minecraft (tránh double)
- Khi có dinocore + dinorace, tab format mặc định: `{prefix}{team}{player}{suffix}`
- Muốn hiện level trong tab: sửa `tab.format` thành `{prefix}{level}{team}{player}{suffix}`