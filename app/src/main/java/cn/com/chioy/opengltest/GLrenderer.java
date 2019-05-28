package cn.com.chioy.opengltest;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLrenderer implements GLSurfaceView.Renderer {

    private FloatBuffer mTriangleBuffer;
    private FloatBuffer mColorBuffer;
    private float[] mTriangleArray = {
            0f, 1f, 0f,
            -1f, -1f, 0f,
            1f, -1f, 0f
    };

    private float[] mColor = new float[]{
            1, 1, 0, 1,
            0, 1, 1, 1,
            1, 0, 1, 1
    };
    public GLrenderer() {
        ByteBuffer bb = ByteBuffer.allocateDirect(mTriangleArray.length * 4);
        bb.order(ByteOrder.nativeOrder());
        mTriangleBuffer = bb.asFloatBuffer();
        mTriangleBuffer.put(mTriangleArray);
        mTriangleBuffer.position(0);

        ByteBuffer bbColor = ByteBuffer.allocateDirect(mColor.length * 4);
        bbColor.order(ByteOrder.nativeOrder());
        mColorBuffer = bbColor.asFloatBuffer();
        mColorBuffer.put(mColor);
        mColorBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(1f,1f,1f,1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float ratio = (float) width / height;

        gl.glViewport(0,0,width,height);

        gl.glMatrixMode(GL10.GL_PROJECTION);

        gl.glLoadIdentity();

        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glTranslatef(0f, 0.0f, -2.0f);

        gl.glVertexPointer(3,GL10.GL_FLOAT,0,mTriangleBuffer);

        gl.glColorPointer(4,GL10.GL_FLOAT,0,mColorBuffer);

        gl.glDrawArrays(GL10.GL_TRIANGLES,0,3);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

        gl.glFinish();
    }
}
