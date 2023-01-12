package exercises.chapter10.e06package2;

import exercises.chapter10.e06package.DataInterface;

public class Server {
    protected class SecretData implements DataInterface {
        public SecretData() {
        }

        @Override
        public void showData() {
            System.out.println("Server.SecretData.showData()");
        }
    }
}
