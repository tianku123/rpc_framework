package framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ConsumerProxy {

    /**
     * 服务消费代理接口
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T consume(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host, port);
                ObjectOutputStream outputStream = null;
                ObjectInputStream inputStream = null;
                try {
                    outputStream = new ObjectOutputStream(socket.getOutputStream());

                    outputStream.writeUTF(method.getName());
                    outputStream.writeObject(args);
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    Object object = inputStream.readObject();
                    if (object instanceof Throwable) {
                        throw (Throwable) object;
                    }
                    return object;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) inputStream.close();
                    if (outputStream != null) outputStream.close();
                    if (socket != null) socket.close();
                }
                return null;
            }
        });
    }
}
