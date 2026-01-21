$baseUrl = "https://raw.githubusercontent.com/rn0x/Adhkar-json/main/audio/27.mp3" # Short Ayatul Kursi or similar
$targetDir = "app\src\main\res\raw"
$sourceFile = "$targetDir\sample_placeholder.mp3"

# Ensure directory exists
if (-not (Test-Path -Path $targetDir)) {
    New-Item -ItemType Directory -Path $targetDir -Force
}

# Download sample file
Write-Host "Downloading sample audio..."
Invoke-WebRequest -Uri $baseUrl -OutFile $sourceFile

if (Test-Path -Path $sourceFile) {
    Write-Host "Sample downloaded successfully."
    
    # Morning Azkar 1-31
    for ($i=1; $i -le 31; $i++) {
        Copy-Item -Path $sourceFile -Destination "$targetDir\morning_$i.mp3" -Force
    }
    Write-Host "Set Morning Azkar (1-31)"

    # Evening Azkar 1-30
    for ($i=1; $i -le 30; $i++) {
        Copy-Item -Path $sourceFile -Destination "$targetDir\evening_$i.mp3" -Force
    }
    Write-Host "Set Evening Azkar (1-30)"

    # Prayer Azkar 1-58
    for ($i=1; $i -le 58; $i++) {
        Copy-Item -Path $sourceFile -Destination "$targetDir\prayer_$i.mp3" -Force
    }
    Write-Host "Set Prayer Azkar (1-58)"
    
    # Clean up source
    Remove-Item -Path $sourceFile
    Write-Host "Done! All audio files set (Placeholder)."
} else {
    Write-Error "Failed to download sample file."
}
