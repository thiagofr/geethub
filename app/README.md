# Geethub Android

Este é um projeto Android que utiliza a arquitetura MVVM (Model-View-ViewModel) e incorpora conceitos de clean code.

## Descrição da Arquitetura MVVM

A arquitetura MVVM é um padrão arquitetural de software que separa a lógica de negócio da interface do usuário. Ela consiste em três componentes principais:

- **Modelo**: Responsável pelos dados e lógica de negócio. Ele encapsula a fonte de dados, realiza operações de leitura/escrita e fornece os dados necessários para a interface do usuário.

- **Visão (View)**: Responsável por exibir a interface do usuário e lidar com as interações do usuário. Ela exibe os dados fornecidos pelo ViewModel e envia eventos de interação do usuário para o ViewModel.

- **ViewModel**: Atua como um intermediário entre o Modelo e a Visão. Ele contém os dados e a lógica necessários para atualizar a Visão com base em alterações no Modelo e processar eventos de interação do usuário da Visão.

No MVVM, a Visão é vinculada ao ViewModel por meio de vinculações de dados, o que permite que a Visão seja atualizada automaticamente sempre que os dados do ViewModel mudarem. Isso facilita a separação de responsabilidades e torna o código mais modular e testável.

## Conceitos de Clean Code

Este projeto segue alguns princípios e práticas de clean code, incluindo:

- **Princípio da Responsabilidade Única (SRP)**: Cada classe e método é projetado para ter uma única responsabilidade, tornando o código mais fácil de manter e entender.

- **Injeção de Dependência (DI)**: As dependências são injetadas nas classes em vez de serem codificadas diretamente, promovendo o baixo acoplamento e facilitando os testes.

- **Convenções de Nomenclatura**: Nomes significativos e descritivos são usados para classes, métodos e variáveis, aumentando a legibilidade do código.

- **Formatação de Código**: É aplicada uma formatação de código consistente e uma indentação adequada, melhorando a clareza e consistência do código.

## Dependências

As seguintes dependências são utilizadas neste projeto:

### Implementação

- androidx.core:core-ktx:1.10.1
- androidx.appcompat:appcompat:1.6.1
- com.google.android.material:material:1.9.0
- androidx.constraintlayout:constraintlayout:2.1.4
- com.squareup.retrofit2:retrofit:2.9.0
- com.squareup.retrofit2:converter-gson:2.9.0
- io.insert-koin:koin-android:3.4.0
- androidx.navigation:navigation-fragment-ktx:2.5.3
- androidx.navigation:navigation-ui-ktx:2.5.3
- com.github.bumptech.glide:glide:4.12.0
- com.airbnb.android:lottie:6.0.0

### Testes

- org.mockito:mockito-core:5.3.1

## Configuração do Ambiente de Desenvolvimento

Para executar e testar o projeto, é necessário configurar o ambiente de desenvolvimento. Siga as etapas abaixo:

1. Clone o repositório do projeto:
   git clone https://github.com/thiagofr/geethub-android.git
2. Abra o projeto no Android Studio.
3. Faça as configurações necessárias para executar o projeto em um dispositivo Android ou emulador.


## Licença

Este projeto está licenciado sob a [Licença MIT](https://opensource.org/licenses/MIT).