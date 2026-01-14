# 🍅 Pomodoro Lofi Focus

![Java](https://img.shields.io/badge/Java-Spring_Boot-orange?style=for-the-badge&logo=springboot)
![Vaadin](https://img.shields.io/badge/Vaadin-Flow_%2B_Vite-00b4f0?style=for-the-badge&logo=vaadin)
![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-purple?style=for-the-badge)
![Tests](https://img.shields.io/badge/Tests-Passing-success?style=for-the-badge&logo=junit5)

**Aplicación Fullstack de productividad que fusiona la técnica Pomodoro con un entorno Lofi inmersivo.**

🔗 **Live Demo:** [https://pomodoro-lofi.onrender.com/](https://pomodoro-lofi.onrender.com/)

---

## 🏗️ Arquitectura y Diseño de Software

Este proyecto ha sido diseñado siguiendo estrictos estándares de la industria para garantizar escalabilidad y mantenibilidad:

### 🔹 Arquitectura Hexagonal (Ports & Adapters)
El código no está acoplado al framework. Se ha separado en capas claras para proteger la lógica de negocio:
- **Domain**: Núcleo puro de la aplicación (Entidades y Lógica del temporizador) sin dependencias externas.
- **Application**: Casos de uso y orquestación de servicios.
- **Infrastructure**: Implementación técnica (UI Web, Configuración).

### 🔹 Principios SOLID
Se han aplicado principios de diseño para lograr un código limpio:
- **Single Responsibility**: Cada clase (Servicios, Vistas, Estados) tiene una única responsabilidad definida.
- **Dependency Injection**: Uso del contenedor de Spring para desacoplar componentes y facilitar el testing.

### 🔹 Calidad y Testing
- **Unit Testing**: Cobertura de tests unitarios (JUnit 5 + Mockito) para validar la lógica crítica del dominio y asegurar que el temporizador funciona con precisión milimétrica.

---

## 🔒 Seguridad y Tecnología Frontend

A diferencia de una SPA tradicional insegura, este proyecto aprovecha la potencia de **Vaadin Flow**:

- **Server-Side Security**: Toda la lógica de negocio y el estado de la sesión residen en el servidor (Java). El cliente (navegador) no manipula la lógica, lo que elimina vulnerabilidades comunes de validación en frontend.
- **Optimización Moderna**: Aunque la lógica es Java, Vaadin compila el frontend usando **Vite**, generando un bundle de JavaScript/TypeScript optimizado y ligero para una experiencia de usuario fluida y reactiva.

---

## 🛠️ Stack Tecnológico

- **Backend**: Java 21, Spring Boot 3.
- **Frontend**: Vaadin Flow 24 (Java-based) .
- **Testing**: JUnit 5, Mockito.
- **Despliegue**: Docker & Render.

---

## 📸 Vista Previa

![Interfaz de Usuario](Interfaz.png)
