**NOTE: This source code for this plugin is out of date!**

**주의: 이 플러그인에 개시되어 있는 소스 코드는 구버전 소스 코드입니다!**

# GrapplingHook-plugin


Supported versions: **spigot 1.8.8**

Tested versions: **spigot 1.8.8**

## 한국어 페이지는, 아래쪽으로 내려가 주세요.

## Reload
This plugin is automatically loaded by editing the config.yml file without having to reload it.

## Functions
 - Even beginners can easily edit and add.
 - You can easily get the created grappling hook.
 - Does not take damage when landing after used grappling hook.
 - You can easily customize the grappling hook.
 - You can set the cool time for each grappling hook.
 - Compatible with almost all Minecraft versions.

## Commands
### `/grapplinghook`, `/gh`
![](https://cdn.discordapp.com/attachments/699227450705444884/820327515821965342/unknown.png)
![](https://cdn.discordapp.com/attachments/699227450705444884/820328172851298314/unknown.png)
![](https://cdn.discordapp.com/attachments/699227450705444884/820330110179737600/unknown.png)

This command checks all grappling hooks.

To use this command, you need the `grapplinghook.command` permissions.

If you click the fishing rod on the screen, you can receive the fishing rod as inventory.

## How to Edit config.yml

 - Syntax (config.yml)

```yml
warn message: '&cWhow! Slow down there! %cooldown%'
invincible time: 1
Grappling Hooks:
  '&aGrappling Hook':
    Multiplier: 3
    Max Y Power: 1.5
    Cooldown: 3
  '&5Super Grappling Hook':
    Multiplier: 5
    Max Y Power: 2.25
    Cooldown: 2
```

`warn message`: This is the message to be printed if you use the grappling hook before the cooldown is over.
 - `%cooltime%`, `%cooldown%`: Cooltime Value

`invincible time`: When landing on the land after use a grappling hook, you take fall damage after that time has elapsed. (Unit: seconds)

```yml
Grappling Hooks:
  '&aGrappling Hook':
  # Fishing rods with that name are recognized as grappling hooks.
  # To add a fishing rod with a different name, enter it in the same way as above in quotation marks.
  # You can use color codes (e.g. &a, &d, &l).
  # Putting '##' in front of a name disables the grappling hook for that name.

    Multiplier: 3
    Max Y Power: 1.5
    Cooldown: 3
```

`Multiplier`: This is the power multiplied to the grappling hook. The default value is 3, the higher the number, the stronger it flies.

 - Example) 1 = x1 power, 2 = x2 power

`Max Y Power`: When using the grappling hook, determine the maximum upward force. The default value is 1.5, which is determined by the position of the grappling hook when zero is entered. The higher the number, the higher it flies.

 - Example) 1 = x1 power, 2 = x2 power

`Cooldown`: This is the cooldown time until reuse after using the grappling hook. If 0 is entered, the cooldown is removed.

 - (Unit: seconds)

## Downloads
Download : [Releases](https://github.com/Mooner510/GrapplingHook-plugin/releases)

## Report bugs and Feedback
**Discord - 무너#4123**

## My Discord:
#### Click to this image:
[![](https://discordapp.com/assets/e4923594e694a21542a489471ecffa50.svg)](https://discord.gg/AbgV8Rz)

***

# 한국어

## 리로드
이 플러그인은, 리로드 할 필요 없이 `config.yml`파일을 수정하면 자동으로 로드됩니다.

## 기능
 - 초보자들도 쉽게 수정하고 추가할 수 있습니다.
 - 설정된 그래플링 훅을 쉽게 꺼낼 수 있습니다.
 - 그래플링 훅을 사용하고 착지할 때 데미지를 받지 않습니다.
 - 쉽게 그래플링 훅을 커스터 마이징 할 수 있습니다.
 - 각 그래플링 훅 마다 쿨타임을 설정할 수 있습니다.
 - 거의 모든 마인크래프트 버전에 호환됩니다.

## 명령어
### `/grapplinghook`, `/gh`
![](https://cdn.discordapp.com/attachments/699227450705444884/820327515821965342/unknown.png)
![](https://cdn.discordapp.com/attachments/699227450705444884/820328172851298314/unknown.png)
![](https://cdn.discordapp.com/attachments/699227450705444884/820330110179737600/unknown.png)

모든 그래플링 훅을 확인하는 명령어입니다.

이 명령어를 사용하기 위해서는, `grapplinghook.command`, `grapplinghook.command.gh` 펄미션이 필요합니다.

해당 화면에서 낚시대를 클릭하면, 낚시대를 인벤토리로 지급받을 수 있습니다.

## config.yml 수정 

 - Syntax [구문]

```yml
warn message: '&cWhow! Slow down there! %cooldown%'
invincible time: 1
Grappling Hooks:
  '&aGrappling Hook':
    Multiplier: 3
    Max Y Power: 1.5
    Cooldown: 3
  '&5Super Grappling Hook':
    Multiplier: 5
    Max Y Power: 2.25
    Cooldown: 2
```

`warn message`: 쿨타임이 끝나기 전에 그래플링 훅을 사용할 경우 출력할 메시지입니다.
 - `%cooltime%`, `%cooldown%`: 쿨타임 값 출력

`invincible time`: 그래플링 훅을 쓰고 바닥에 착지 후, 해당 시간이 지나고 난 후 부터 낙하 피해를 받습니다. (단위: 초)

```yml
Grappling Hooks:
  '&aGrappling Hook':
  # 해당 이름으로 된 낚시대를 그래플링 훅으로 인식합니다.
  # 다른 이름으로 된 낚시대를 추가하기 위해선 따옴표 안에 위와 같은 방법으로 입력하면 됩니다.
  # 색 코드를 사용할 수 있습니다. (예: &a, &d, &l)

    Multiplier: 3
    Max Y Power: 1.5
    Cooldown: 3
```

`Multiplier`: 그래플링 훅으로 날아가는 힘의 배율입니다. 기본값은 3이며, 숫자가 높을 수록 더 강하게 날아갑니다.

 - 예) 1 = 1배, 2 = 2배, 3 = 3배

`Max Y Power`: 그래플링 훅을 사용시, 최대 상승력을 정합니다. 기본값은 1.5이고, 0이 입력될 경우 제한이 사라지며, 그래플링 훅의 위치에 따라 정해집니다. 숫자가 높을 수록 더 높게 날아갑니다.

 - 예) 1 = 1배, 2 = 2배, 3 = 3배

`Cooldown`: 해당 그래플링 훅을 사용하고 난 후, 재사용까지의 쿨타임입니다. 0이 입력될 경우 쿨타임이 제거됩니다.

 - 단위: 초

## 다운로드
다운로드는, [Releases](https://github.com/Mooner510/GrapplingHook-plugin/releases)를 참고하세요.

## 버그 제보 및 업데이트 요청
**Discord - 무너#4123**

## 디스코드
#### Click to this image:
[![](https://discordapp.com/assets/e4923594e694a21542a489471ecffa50.svg)](https://discord.gg/AbgV8Rz)
