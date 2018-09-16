package framework;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProviderReflect {

    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 服务的发布
     *
     */
    public static void provider(final Object service, int port) throws Exception {
        final ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(new Runnable() {
                public void run() {
                    ObjectInputStream inputStream = null;
                    ObjectOutputStream outputStream = null;
                    try {
                        inputStream = new ObjectInputStream(socket.getInputStream());
                        // 方法名称
                        String methodName = inputStream.readUTF();
                        // 方法参数
                        Object[] args = (Object[]) inputStream.readObject();
                        outputStream = new ObjectOutputStream(socket.getOutputStream());
                        // 方法引用
                        Object result = MethodUtils.invokeExactMethod(service, methodName, args);
                        outputStream.writeObject(result);
                    } catch (Exception e) {
                        try {
                            outputStream.writeObject(e);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        e.printStackTrace();
                    } finally {
                        try {
                            if (inputStream != null) inputStream.close();
                            if (outputStream != null) outputStream.close();
                            if (socket != null) socket.close();
                        } catch (Exception e) {

                        }
                    }
                }
            });
        }
    }
}
