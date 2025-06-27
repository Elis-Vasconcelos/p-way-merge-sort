# Ordenação Externa p-way Merge Sort em Java

Este projeto é uma implementação de um algoritmo de ordenação externa (external sort) balanceado de p-vias (p-way merge sort) em Java. Foi desenvolvido como requisito para a disciplina MATA54 - Estrutura de Dados e Algoritmos II da UFBA.

O programa foi projetado para ordenar arquivos de números inteiros que são grandes demais para caber na memória principal, utilizando o método de "Seleção por Substituição" para gerar as corridas (sequências) ordenadas iniciais e um min-heap para a fase de intercalação (merge).

---

## Execução na Nuvem (Gitpod)

Para facilitar a correção e garantir a compatibilidade, este projeto está configurado para ser executado na nuvem com o Gitpod.

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#/https://github.com/Elis-Vasconcelos/p-way-merge-sort)

### Instruções de Teste no Gitpod

1.  Clique no botão **Open in Gitpod** acima. Pode ser necessário se autenticar com sua conta do GitHub.
2.  Aguarde o ambiente carregar. Isso pode levar um minuto na primeira vez.
3.  Um terminal aparecerá na parte de baixo da tela com a mensagem "✅ Ambiente pronto!". O código já estará compilado.
4.  Use este terminal para executar os casos de teste. Por exemplo:

    ```bash
    # Teste com p = 3
    java PWayMergeSort 3 input.txt output_p3.txt
    ```
5.  Após a execução, os arquivos de saída (como `output_p3.txt`) aparecerão na lista de arquivos à esquerda e podem ser abertos para verificar o resultado.

### Execução Local (Alternativa)

Caso prefira executar o projeto localmente, siga estes passos.

#### Pré-requisitos
* Java Development Kit (JDK) versão 8 ou superior.

#### Passos
1.  **Clone o Repositório:**
    ```bash
    git clone https://github.com/Elis-Vasconcelos/p-way-merge-sort.git
    cd p-way-merge-sort
    ```
2.  **Compile o Código:**
    ```bash
    # Este único comando compila todos os arquivos .java necessários
    javac --release 8 PWayMergeSort.java
    ```
3.  **Execute e Teste:**
    ```bash
    # Exemplo para p = 2
    java PWayMergeSort 2 input.txt output_p2.txt
    ```
