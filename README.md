# FRC Robot Code — WPILib 2027 Alpha 5

Robot code for our FRC team, built with **WPILib 2027 Alpha 5**, **GradleRIO 2027.0.0-alpha-5**, and **Commands v3**. Targets the **SystemCore** controller running **Java 25**.

---

## Project Structure

```
src/main/java/first/robot/
├── Robot.java            # Main robot class (TimedRobot)
├── RobotContainer.java   # Subsystems, OI devices, button bindings
├── Constants.java        # Robot-wide constants
├── commands/             # Command factories (coroutine-based)
├── subsystems/           # Mechanism subclasses
└── util/                 # Shared utilities
vendordeps/               # Vendor library JSON files
docs/                     # Team documentation
```

> **Note:** This project uses the `first.robot` package. The Commands v3 API (`org.wpilib.command3`) replaces the legacy `edu.wpi.first.wpilibj2.command` API entirely — do not mix them.

---

## Commands v3 Quick Reference

Commands v3 introduces coroutine-based commands. Key differences from older versions:

| Concept | WPILib 2027 / Commands v3 | ❌ Old (do not use) |
|---|---|---|
| Base package | `org.wpilib.command3` | `edu.wpi.first.wpilibj2.command` |
| Subsystem base | `Mechanism` | `SubsystemBase` |
| Scheduler | `Scheduler.getDefault()` | `CommandScheduler.getInstance()` |
| Controller | `CommandGamepad` | `CommandXboxController` |
| Command body | `Mechanism.run(coro -> { ... })` | `initialize/execute/isFinished` |
| Multi-command | `Command.requiring(...).executing(coro -> { ... })` | `SequentialCommandGroup` |

---

## Setup

1. Install **WPILib 2027 Alpha 5** from [https://github.com/wpilibsuite/allwpilib/releases](https://github.com/wpilibsuite/allwpilib/releases)
2. Open this project in VS Code with the WPILib extension installed
3. Java 25 is required — the WPILib installer includes a bundled JDK

---

## Build

```bash
./gradlew build
```

---

## Deploy

Deploys to the SystemCore controller over USB or Wi-Fi:

```bash
./gradlew deploy
```

Set your team number in `.wpilib/wpilib_preferences.json` before deploying.

---

## Simulation

```bash
./gradlew simulateJava
```

---

## Branch Workflow

```
feature/your-feature → PR → code review → squash merge → main
```

- All work happens on **feature branches**
- Open a **Pull Request** to merge into `main`
- At least **1 reviewer** must approve
- CI (**Build** workflow) must pass before merging
- Merge via **squash merge** to keep history clean

---

## Team Git Rules

- **No direct commits to `main`** — all changes go through a PR
- **All changes require a PR** — even small fixes
- **CI must pass** — the build workflow must succeed before merging
- **Keep `main` competition-stable** — if it's in `main`, it works on the robot
- **Tag working robot states before events** — e.g., `git tag v1.0-regional-2027` before competition so you can always roll back

---

## CI Workflows

Three automated workflows run on every push and PR:

| Workflow | What it does |
|---|---|
| **Build** | Runs `./gradlew build` to make sure the code compiles |
| **Gradle Wrapper Validation** | Verifies the Gradle wrapper JAR hasn't been tampered with |
| **CodeQL** | Scans Java code for security vulnerabilities |

---

## Branch Protection

To enforce branch protection rules on `main`, run the setup script after authenticating with the GitHub CLI:

```bash
# 1. Install GitHub CLI: https://cli.github.com/
# 2. Authenticate:
gh auth login
# 3. Run the script:
bash scripts/setup-branch-protection.sh
```

See `scripts/setup-branch-protection.sh` for full details.

---

## License

WPILib BSD License — see `WPILib-License.md`
