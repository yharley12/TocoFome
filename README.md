# TocoFome — Semana 3

Versão didática para a atividade de Programação Orientada a Objetos. O projeto usa Java básico para mostrar pacotes, classes, atributos privados, herança, interface e um fluxo simples de delivery.

## Organização

- `app`: contém a classe `Main`, que executa a demonstração.
- `model`: contém as classes do sistema.
- `interfaces`: contém a interface `ConsultavelStatus`.

## Conceitos usados

- **Encapsulamento:** os atributos das classes são privados e acessados por métodos.
- **Herança:** `Cliente`, `Restaurante` e `Entregador` herdam de `Usuario`.
- **Classe abstrata:** `Usuario` não representa um perfil específico e declara o método `getTipo()`.
- **Interface:** `Pedido` e `Entrega` implementam `ConsultavelStatus`.
- **Polimorfismo:** `Pedido` e `Entrega` são consultados por uma mesma lista de `ConsultavelStatus`.

## Como executar

É necessário ter o JDK instalado. No terminal, dentro desta pasta, execute:

### Windows (PowerShell)

```powershell
New-Item -ItemType Directory -Force out
$arquivos = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $arquivos
java -cp out br.com.tocofome.app.Main
```

### Linux/macOS/Git Bash

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src/main/java -name '*.java')
java -cp out br.com.tocofome.app.Main
```
