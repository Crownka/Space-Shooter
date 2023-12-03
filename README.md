Space Shooter

(relatório aqui)


1 - Sobre o metodo Paint e PaintComponent(Da Classe Fase)

O método paintComponent é o mesmo método do paint. A diferença é que, ao sobrescrever o paintComponent, você está utilizando uma prática recomendada ao trabalhar com Swing em Java.

O método paint é o método padrão da classe JPanel para desenhar componentes. No entanto, ao trabalhar com Swing, é comum sobrescrever o paintComponent em vez do paint. Isso ocorre porque o paintComponent é responsável por desenhar apenas o conteúdo do componente, enquanto o paint pode ser utilizado para desenhar o conteúdo e a borda do componente.

Ao sobrescrever o paint, você pode acabar desenhando sobre a borda do componente, o que pode não ser desejado. Portanto, ao usar o JPanel, é uma boa prática sobrescrever o paintComponent para garantir que você esteja lidando apenas com o conteúdo interno do componente.

Em resumo, o paintComponent é uma versão mais específica e adequada para desenho interno em componentes Swing, enquanto o paint é mais geral e pode ser usado para desenhar tanto o conteúdo quanto a borda do componente.

A classe JPanel é uma classe Swing que é frequentemente usada para conter outros componentes e fornecer uma área onde você pode desenhar. Ao sobrescrever o método paintComponent em vez do paint, você segue a prática recomendada para desenho interno em componentes Swing.

tive que ficar hora um tempinho pra entender como usar o JPanel(deixarei o Paint no código para caso precise)



2 - Para criar os inimigos, comecei fazendo uma lista fixa que era criada assim que a fase iniciava, vi que não ficava legal pois depois de um tempo os inimigos paravam de vir

então depois tentei fazer uma lista segura com iterator do próprio java, não consegui fazer funcionar como esperava, então apenas fiz listas diferentes que interagem entre si, uma de inimigos a surgir e outra dos próprios inimigos surgindo, também fiz um remove sempre que eles colidem com os tiros, player ou saem da tela, basicamente eles ficam invisiveis, e sempre que ficam invisiveis, são deletados, assim economizando dados e deixando mais otimizado