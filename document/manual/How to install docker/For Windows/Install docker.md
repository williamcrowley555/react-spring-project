# Install Docker
## Steps 1: Install wsl
1. Open `PowerShell` as Administrator and run
```Powershell
dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
```
2. Continue run
```Powershell
dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
```
Restart your machine to complete the WSL install and update to WSL 2.

3. Visit <a href="https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi"> this link </a> to download the latest WSL2 Linux kernel update package.

4. Open PowerShell and run this command to set WSL 2 as the default version when installing a new Linux distribution:
```Powershell
wsl --set-default-version 2
```
5. Open the `Microsoft Store` and select your favorite Linux distribution.

Use these following:

    Ubuntu 18.04 LTS
    Ubuntu 20.04 LTS
    openSUSE Leap 15.1
    SUSE Linux Enterprise Server 12 SP5
    SUSE Linux Enterprise Server 15 SP1
    Kali Linux
    Debian GNU/Linux
    Fedora Remix for WSL
    Pengwin
    Pengwin Enterprise
    Alpine WSL

From the distribution's page, select `Get`.

## Steps 2: Install docker
1. Visit <a href="https://docs.docker.com/docker-for-windows/install/"> this link </a> and click `Docker Desktop for Windows` button to download the latest Docker.
2. Run the downloaded file to install Docker.
## Finish