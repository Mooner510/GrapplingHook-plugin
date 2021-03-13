# GrapplingHook-plugin

Supported versions: **spigot 1.8-1.16**
Tested versions: **spigot 1.8.8, 1.16.2**

> **List of supported versions:**
> - spigot (1.8.x) : 1.8, 1.8.3, 1.8.4, 1.8.5, 1.8.6, 1.8.7, **1.8.8**
> - spigot (1.9.x) : 1.9, 1.9.2, 1.9.4
> - spigot (1.10.x) : 1.10, 1.10.2
> - spigot (1.11.x) : 1.11, 1.11.1
> - spigot (1.12.x) : 1.12, 1.12.1, 1.12.2
> - spigot (1.13.x) : 1.13, 1.13.1, 1.13.2
> - spigot (1.14.x) : 1.14, 1.14.1, 1.14.2, 1.14.3, 1.14.4
> - spigot (1.15.x) : 1.15, 1.15.1, 1.15.2
> - spigot (1.16.x) : 1.16.1, **1.16.2**, 1.16.3, 1.16.4, 1.16.5

## How to plugin reload
이 플러그인은, 리로드 할 필요 없이 `config.yml`파일을 수정하면 자동으로 로드됩니다.

## Commands
### `/grapplinghook`, `/gh`
![](https://cdn.discordapp.com/attachments/699227450705444884/820327515821965342/unknown.png)
![](https://cdn.discordapp.com/attachments/699227450705444884/820328172851298314/unknown.png)
![](https://cdn.discordapp.com/attachments/699227450705444884/820330110179737600/unknown.png)

모든 그래플링 훅을 확인하는 명령어입니다.

이 명령어를 사용하기 위해서는, `grapplinghook.command`, `grapplinghook.command.gh` 펄미션이 필요합니다.

해당 화면에서 낚시대를 클릭하면, 낚시대를 인벤토리로 지급받을 수 있습니다.

## How to Edit config.yml

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

## Downloads
다운로드는, [Releases](https://github.com/Mooner510/GrapplingHook-plugin/releases)를 참고하세요.

## Report bugs and feedback
**Discord - 무너#0510**

## Join my Discord!
#### Click to this image:
[![](https://discordapp.com/assets/e4923594e694a21542a489471ecffa50.svg)](https://discord.gg/AbgV8Rz)
