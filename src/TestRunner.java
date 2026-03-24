import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

/**
 * Simple Test Runner para ejecutar tests de JUnit sin IDE
 * Ejecuta todos los métodos que comienzan con "test" en una clase
 */
public class TestRunner {
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    
    public static void main(String[] args) {
        System.out.println("=== EJECUTOR DE TESTS ===\n");
        
        runTestClass(ConcursoTest.class);
        
        System.out.println("\n=== RESUMEN DE TESTS ===");
        System.out.println("Total: " + totalTests);
        System.out.println("Pasados: " + passedTests);
        System.out.println("Fallidos: " + failedTests);
        System.out.printf("Cobertura aproximada: %.2f%%\n", (passedTests / (double) totalTests) * 100);
        
        if (failedTests > 0) {
            System.exit(1);
        }
    }
    
    public static void runTestClass(Class<?> testClass) {
        System.out.println("Ejecutando tests de: " + testClass.getSimpleName() + "\n");
        
        Method[] methods = testClass.getDeclaredMethods();
        
        for (Method method : methods) {
            if (method.getName().startsWith("test")) {
                totalTests++;
                try {
                    // Crear instancia y ejecutar setUp
                    Object testInstance = testClass.getDeclaredConstructor().newInstance();
                    Method setUpMethod = testClass.getDeclaredMethod("setUp");
                    setUpMethod.invoke(testInstance);
                    
                    // Ejecutar el test
                    method.invoke(testInstance);
                    
                    System.out.println("✓ " + method.getName());
                    passedTests++;
                } catch (InvocationTargetException e) {
                    System.out.println("✗ " + method.getName());
                    System.out.println("  Causa: " + e.getCause().getMessage());
                    failedTests++;
                } catch (Exception e) {
                    System.out.println("✗ " + method.getName());
                    System.out.println("  Error: " + e.getMessage());
                    failedTests++;
                }
            }
        }
    }
}

