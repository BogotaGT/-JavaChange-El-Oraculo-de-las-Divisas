# JavaChange: El Oráculo de las Divisas

## Descripción
JavaChange es un conversor de monedas avanzado desarrollado en Java como parte del Challenge de Alura Latam y Oracle. Esta aplicación de consola permite a los usuarios realizar conversiones precisas entre múltiples monedas utilizando tasas de cambio en tiempo real obtenidas a través de la API de ExchangeRate.

## Características Principales
- Conversión entre 10 monedas: USD, EUR, GBP, JPY, ARS, BRL, CLP, COP, MXN, y CAD.
- Interfaz de consola intuitiva con menú interactivo.
- Utilización de la API ExchangeRate para tasas de cambio actualizadas.
- Manejo robusto de errores y excepciones.
- Historial de conversiones realizadas.
- Formato de entrada flexible para cantidades.

## Mejoras Recientes
- Implementación de constantes para mejorar la mantenibilidad del código.
- Uso de bloques de texto (Text blocks) para mejorar la legibilidad del código ASCII art.
- Refactorización del bucle principal para un mejor control de flujo.
- Mejora en el manejo de recursos con try-with-resources.
- Optimización del manejo de excepciones.

## Estructura del Código

### Clase Principal
- Método `main`: Controla el flujo principal del programa.
- `mostrarTitulo()`: Muestra un banner ASCII art al inicio.
- `solicitarMoneda()`: Maneja la selección de monedas por el usuario.
- `solicitarCantidad()`: Procesa la entrada de cantidades a convertir.
- `mostrarMenuPostConversion()`: Gestiona las opciones después de cada conversión.
- `dibujarMarco()`: Crea marcos decorativos para los mensajes en consola.

### Clase ConvertidorMoneda
- `actualizarTasasCambio()`: Obtiene las tasas de cambio actualizadas de la API.
- `convertir()`: Realiza el cálculo de conversión entre monedas.
- Utiliza HttpClient para conexiones API y Gson para procesamiento JSON.

## Cómo Usar
1. Ejecute la aplicación.
2. Seleccione la moneda de origen.
3. Ingrese la cantidad a convertir.
4. Seleccione la moneda de destino.
5. Visualice el resultado de la conversión.
6. Elija entre realizar otra conversión, ver el historial o salir.

## Instalación y Ejecución
1. Clone el repositorio: `git clone https://github.com/BogotaGT/JavaChange-El-Oraculo-de-las-Divisas.git`
2. Abra el proyecto en su IDE preferido (se recomienda IntelliJ IDEA).
3. Asegúrese de tener Java 21 y las dependencias necesarias (Gson).
4. Ejecute la clase `Principal.java`.

## Dependencias
- Java 21
- Gson 2.10.1
- API ExchangeRate

## Contribuir
Las contribuciones son bienvenidas. Por favor, abra un issue para discutir cambios mayores antes de hacer un pull request.

## Autor
Juan Carlos Bogotá Castañeda (BogotaGT) - Desarrollador

## Licencia
Este proyecto está bajo la Licencia MIT - vea el archivo LICENSE.md para más detalles.

## Agradecimientos
- Alura Latam y Oracle por el desafío y la formación.
- ExchangeRate API por proporcionar las tasas de cambio en tiempo real.
