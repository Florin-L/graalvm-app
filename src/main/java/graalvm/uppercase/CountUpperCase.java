package graalvm.uppercase;

public class CountUpperCase {
  static final int ITERATIONS = Math.max(Integer.getInteger("iterations", 1), 1);

  public static void main(String... args) {
    System.out.printf("#ITERATIONS=%d%n", ITERATIONS);
    var sentence = String.join(" ", args);
    for (int i = 0; i < ITERATIONS; i++) {
      if (ITERATIONS != 1) {
        System.out.println("-- iteration " + (i + 1));
      }

      long total = 0;
      long start = System.currentTimeMillis();
      long last = start;

      for (int j = 1; j < 10_000_000; j++) {
        total += sentence.chars().filter(Character::isUpperCase).count();
        if (j % 1_000_000 == 0) {
          long now = System.currentTimeMillis();
          System.out.printf("%d (%d ms)%n", j / 1_000_000, now - last);
          last = now;
        }
      }
      System.out.printf("total: %d (%d ms)%n", total, System.currentTimeMillis() - start);
    }
  }
}
