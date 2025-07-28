#!/bin/bash

# Ir para o diretório do projeto (raiz)
cd "$(dirname "$0")" || exit 1

SRC_DIR="src"
JAR_DIR="$SRC_DIR/jar"
MAIN_CLASS="Main"
JAR_NAME="Forca.jar"
MANIFEST="$JAR_DIR/manifest.txt"

echo "Compilando arquivos .java..."
find "$SRC_DIR" -name "*.java" > sources.txt
javac @sources.txt

# Verifica se compilação foi bem-sucedida
if [ $? -ne 0 ]; then
    echo "❌ Erro na compilação."
    rm -f sources.txt
    exit 1
fi
rm -f sources.txt
echo "✅ Compilação concluída."

echo "Criando diretório para o JAR se necessário..."
mkdir -p "$JAR_DIR"

echo "Criando arquivo manifest.txt..."
cat > "$MANIFEST" <<EOF
Main-Class: $MAIN_CLASS
Class-Path: .
EOF

echo "Gerando arquivo JAR..."
jar cfm "$JAR_DIR/$JAR_NAME" "$MANIFEST" -C "$SRC_DIR" .

if [ $? -ne 0 ]; then
    echo "❌ Erro ao criar o JAR."
    exit 1
fi

echo "✅ JAR criado em $JAR_DIR/$JAR_NAME"


echo "Criando script Forca.sh (Linux)..."
cat > Forca.sh << 'EOF'
#!/bin/bash
cd "$(dirname "$0")/src/jar"
if command -v gnome-terminal &> /dev/null; then
    gnome-terminal --title="Jogo da Forca" -- bash -c "java -jar Forca.jar; exec bash"
elif command -v konsole &> /dev/null; then
    konsole --new-tab -p tabtitle='Jogo da Forca' -e bash -c "java -jar Forca.jar; exec bash"
elif command -v xfce4-terminal &> /dev/null; then
    xfce4-terminal --title="Jogo da Forca" -e "bash -c 'java -jar Forca.jar; exec bash'"
else
    echo "No supported terminal emulator found. Running in current terminal."
    # Set title in current terminal (works in most bash terminals)
    echo -ne "\033]0;Jogo da Forca\007"
    java -jar Forca.jar
fi
EOF
chmod +x Forca.sh
echo "✅ Script Forca.sh criado."


echo "Criando script Forca.bat (Windows)..."
cat > Forca.bat <<EOF
@echo off
cd /d "%~dp0src\jar"
start "Jogo da Forca" cmd /k "title Jogo da Forca && java -jar Forca.jar"
EOF
echo "✅ Script Forca.bat criado."

echo "✅ Build finalizado com sucesso!"
echo "Use './Forca.sh' no Linux ou 'Forca.bat' no Windows para iniciar o jogo."