#!/bin/bash

# Go to project root (script directory)
cd "$(dirname "$0")" || exit 1

echo "Creating Forca.sh launcher script..."

cat > Forca.sh << 'EOF'
#!/bin/bash
cd "$(dirname "$0")/src"
# Open new terminal window running the Java game
if command -v gnome-terminal &> /dev/null; then
    gnome-terminal -- bash -c "java -cp . Menu; exec bash"
elif command -v konsole &> /dev/null; then
    konsole -e bash -c "java -cp . Menu; exec bash"
elif command -v xfce4-terminal &> /dev/null; then
    xfce4-terminal -e "bash -c 'java -cp . Menu; exec bash'"
else
    echo "No supported terminal emulator found. Running in current terminal."
    java -cp . Menu
fi
EOF

echo "Creating Forca.bat launcher script..."

cat > Forca.bat << EOF
@echo off
cd /d "%~dp0src"
start cmd /k java -cp . Menu
EOF

echo "Setting execution permission on Forca.sh..."
chmod +x Forca.sh

echo "Build complete!"
echo "Run Forca.sh on Linux or Forca.bat on Windows to start the game."
