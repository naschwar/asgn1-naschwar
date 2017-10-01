final class Viewport
{
   private int row;
   private int col;
   private final int numRows;
   private final int numCols;


   public Viewport(int numRows, int numCols)
   {
      this.numRows = numRows;
      this.numCols = numCols;
   }

   public int getNumRows(){
      return numRows;
   }

   public int getNumCols(){
      return numCols;
   }

   public int getCol(){
      return col;
   }

   public int getRow(){
      return row;
   }

   public Point viewportToWorld(int col, int row)
   {
      return new Point(col + this.col, row + this.row);
   }
   public Point worldToViewport(int col, int row)
   {
      return new Point(col - this.col, row - this.row);
   }
   public boolean contains(Point p)
   {
      return p.y >= row && p.y < row + numRows &&
              p.x >= col && p.x < col + numCols;
   }

   public void shift(int col, int row)
   {
      col = col;
      row = row;
   }
}
