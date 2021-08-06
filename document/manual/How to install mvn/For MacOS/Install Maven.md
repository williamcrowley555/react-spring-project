# Install Apache Maven
## Steps 1: Install Homebrew and latest Java
1. Open MacOS `Terminal`, enter
```MacOS
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```
2. Wait for Homebrew to install
3. Enter `brew search java` to find all available Java-related formula.
```MacOS
% brew search java
```
4. Install latest Java
```MacOS
% brew install java
```
5. Homebrew will install java in the directories at `/usr/local/Cellar/openjdk/`, and symbolic at `/usr/local/opt/openjdk`
```MacOS
% ls -lsa /usr/local/Cellar/openjdk/

total 0
0 drwxr-xr-x   3 mkyong  staff    96 Dec  9 09:06 .
0 drwxrwxr-x  69 mkyong  admin  2208 Jan 15 15:35 ..
0 drwxr-xr-x   9 mkyong  staff   288 Jan 15 16:47 15.0.1

% ls -lsa /usr/local/opt/openjdk
0 lrwxr-xr-x  1 mkyong  admin  24 Dec  9 09:06 /usr/local/opt/openjdk -> ../Cellar/openjdk/15.0.1
```
6. The `java` formula is keg-only, which means it is installed in `/usr/local/Cellar` but not linked into places like `/usr/local/bin` or `/Library/Java/JavaVirtualMachines/` (macOS `/usr/bin/java` wrapper).

For macOS `/usr/bin/java wrapper` to find the installed JDK, we manually create a symbolic link at `/Library/Java/JavaVirtualMachines/`.

```MacOS
% sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
```

7. When installation is done, you can double-check the install.

```MacOS
% java -version

openjdk version "15.0.1" 2020-10-20
OpenJDK Runtime Environment (build 15.0.1+9)
OpenJDK 64-Bit Server VM (build 15.0.1+9, mixed mode, sharing)
```
## Steps 2: Install Apache Maven
1. Open `Terminal`, type The command `brew install maven` will install the latest Maven.

```MacOS
% brew install maven
```
2. Done. The Maven is installed on macOS.
```MacOS
% mvn -version

Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3_1/libexec
Java version: 15.0.1, vendor: N/A, runtime: /usr/local/Cellar/openjdk/15.0.1/libexec/openjdk.jdk/Contents/Home
Default locale: en_MY, platform encoding: UTF-8
OS name: "mac os x", version: "10.16", arch: "x86_64", family: "mac"
```

## Finish