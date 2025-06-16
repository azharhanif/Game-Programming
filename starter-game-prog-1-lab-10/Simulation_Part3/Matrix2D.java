public class Matrix2D  
{
    private double[][] m;

    public Matrix2D()
    {
        m = new double[3][3];
        
        // initialize to identity matrix
        for (int i=0; i<3; ++i)
        {
            for (int j=0; j<3; ++j)
            {
                if (i == j)
                {
                    m[i][j] = 1.0;
                }
                else
                {
                    m[i][j] = 0.0;
                }
            }
        }
    }

    public Vector2D transform(Vector2D v)
    {
        // With 0 as homogeneous coordinate, we ignore the last column (translation)
        return new Vector2D(m[0][0]*v.getX() + m[0][1]*v.getY(),
                            m[1][0]*v.getX() + m[1][1]*v.getY());
    }

    public Point2D transform(Point2D p)
    {
        return new Point2D(m[0][0]*p.getX() + m[0][1]*p.getY() + m[0][2],
                           m[1][0]*p.getX() + m[1][1]*p.getY() + m[1][2]);
    }
    
    public static Matrix2D translation(Vector2D t)
    {
        Matrix2D mat = new Matrix2D();
        mat.m[0][2] = t.getX();
        mat.m[1][2] = t.getY();
        
        return mat;
    }    

    public static Matrix2D scaling(double factor)
    {
        Matrix2D mat = new Matrix2D();
        mat.m[0][0] = mat.m[1][1] = factor;
        
        return mat;
    }    

    public static Matrix2D rotation(double angleDegrees)
    {
        Matrix2D mat = new Matrix2D();
        double angleRad = Math.toRadians(angleDegrees);
        mat.m[0][0] = mat.m[1][1] = Math.cos(angleRad);
        mat.m[1][0] = Math.sin(angleRad);
        mat.m[0][1] = - Math.sin(angleRad);
        
        return mat;
    }    
    
    public static Matrix2D concatenate(Matrix2D lhs, Matrix2D rhs)
    {
        Matrix2D mat = new Matrix2D();
        
        for (int i=0; i<3; ++i)
        {
            for (int j=0; j<3; ++j)
            {
                mat.m[i][j] = lhs.m[i][0]*rhs.m[0][j] +
                              lhs.m[i][1]*rhs.m[1][j] +
                              lhs.m[i][2]*rhs.m[2][j];
            }
        }
        
        return mat;
    }
    
    public static Matrix2D worldToWindow(Point2D cameraCenter, double cameraWidth, Vector2D windowSize)
    {
        // This matrix will allow transforming points from the infinite world to window coordinates
        // Make sure the camera has the same aspect ratio as the window
        double cameraHeight = cameraWidth * windowSize.getY() / windowSize.getX();
        
        // World Top Left coordinate maps to (0,0) in the Window
        // World Bottom Right coordinate maps to (width -1, height-1) in the Window 
        Point2D worldTopLeft = new Point2D(cameraCenter.getX() - cameraWidth / 2,
                                           cameraCenter.getY() + cameraHeight / 2);
                                           
        Point2D worldBottomRight = new Point2D(cameraCenter.getX() + cameraWidth / 2,
                                               cameraCenter.getY() - cameraHeight / 2);
                                               
        Point2D windowBottomRight = new Point2D(windowSize.getX() - 1.0,
                                                windowSize.getY() - 1.0);  
                         
        // The Viewport Matrix can be obtained by solving a system of linear equations mapping
        // the top left and bottom right coordinates from the world to the window
        Matrix2D mat = new Matrix2D();
        
        mat.m[0][0] = windowBottomRight.getX() / (worldBottomRight.getX() - worldTopLeft.getX());
        mat.m[1][1] = windowBottomRight.getY() / (worldBottomRight.getY() - worldTopLeft.getY());
        mat.m[0][2] = - windowBottomRight.getX() * worldTopLeft.getX() / 
                        (worldBottomRight.getX() - worldTopLeft.getX());
        mat.m[1][2] = - windowBottomRight.getY() * worldTopLeft.getY() /
                        (worldBottomRight.getY() - worldTopLeft.getY());
                        
        return mat;
    }

    public static Matrix2D windowToWorld(Point2D cameraCenter, double cameraWidth, Vector2D windowSize)
    {
        // This matrix will allow transforming points from the infinite world to window coordinates
        // Make sure the camera has the same aspect ratio as the window
        double cameraHeight = cameraWidth * windowSize.getY() / windowSize.getX();
        
        // World Top Left coordinate maps to (0,0) in the Window
        // World Bottom Right coordinate maps to (width -1, height-1) in the Window 
        Point2D worldTopLeft = new Point2D(cameraCenter.getX() - cameraWidth / 2,
                                           cameraCenter.getY() + cameraHeight / 2);
                                           
        Point2D worldBottomRight = new Point2D(cameraCenter.getX() + cameraWidth / 2,
                                               cameraCenter.getY() - cameraHeight / 2);
                                               
        Point2D windowBottomRight = new Point2D(windowSize.getX() - 1.0,
                                                windowSize.getY() - 1.0);  
                         
        // The Viewport Matrix can be obtained by solving a system of linear equations mapping
        // the top left and bottom right coordinates from the window to the world
        Matrix2D mat = new Matrix2D();
        
        mat.m[0][0] = (worldBottomRight.getX() - worldTopLeft.getX()) / windowBottomRight.getX();
        mat.m[1][1] = (worldBottomRight.getY() - worldTopLeft.getY()) / windowBottomRight.getY();
        mat.m[0][2] = worldTopLeft.getX();
        mat.m[1][2] = worldTopLeft.getY();
                        
        return mat;
    }

}
