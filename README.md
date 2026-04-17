# Simulador de Investimento

Este é um projeto de estudos desenvolvido com o objetivo de treinar e aplicar os principais Design Patterns do universo da programação orientada a objetos. Os padrões focados neste projeto são:

- **Strategy**
- **Factory**

## Objetivo

O projeto consiste em um simulador de investimentos que pode ser facilmente extensível e flexível graças ao uso dos design patterns. A ideia é permitir a simulação de diferentes tipos de investimentos, estratégias e cálculos, demonstrando na prática como a aplicação dos padrões Strategy e Factory pode facilitar a manutenção e expansão de um sistema.

## Principais Design Patterns Utilizados

- **Strategy:** Permite alternar entre diferentes estratégias de cálculo de investimento em tempo de execução, separando a lógica de cada estratégia em classes distintas.
- **Factory:** Centraliza e abstrai a criação dos objetos de investimento, facilitando a adição de novas opções sem modificar o código existente.

## Como executar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/gabriel-afd/simulador_investimento.git
   ```
2. Navegue até a pasta do projeto:
   ```bash
   cd simulador_investimento
   ```
3. Siga as instruções específicas na pasta do seu projeto para rodar o simulador (exemplo: `python main.py`, `dotnet run`, etc).

## Estrutura do Projeto

Explora principalmente os conceitos de Orientação a Objetos, SOLID, além dos padrões de projeto já mencionados.

```
src/
 ├── investimentos/     # Estratégias e tipos de investimento
 ├── factory/           # Classes Factory para criação de investimentos
 └── main.xxx           # Arquivo principal de execução
```

## Licença

Este projeto é distribuído sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

---

Projeto criado para fins de estudo e aprimoramento das práticas de design de software.
