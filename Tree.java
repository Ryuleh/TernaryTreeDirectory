// Source code is decompiled from a .class file using FernFlower decompiler.
class Tree {
   private Node root;

   public Tree(String var1) {
      this.root = new Node(var1, true);
   }

   public Node getRoot() {
      return this.root;
   }

   public void addDirectory(Node var1, String var2) {
      Node var3 = new Node(var2, true);
      var1.addChild(var3);
   }

   public void removeDirectory(Node var1, Node var2) {
      var1.removeChild(var2);
   }
}
