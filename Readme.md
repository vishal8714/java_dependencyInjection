# Spring Dependency Injection Guide

This repository serves as a quick reference guide and practical example for understanding Dependency Injection (DI) and Inversion of Control (IoC) in the Spring Framework.

## 🧠 Core Concepts

*   **Dependency Inversion Principle (DIP):** A software design principle stating that classes should **depend on abstractions** (interfaces) rather than concrete implementations.
*   **Dependency Injection (DI):** The implementation technique used to achieve DIP. Instead of a class creating its own dependencies, an external framework (like Spring) provides them.
*   **Inversion of Control (IoC):** Spring takes full responsibility for the creation, management, and entire lifecycle of objects (Spring Beans).

## 💉 Types of Injection

1.  **Field Injection (`@Autowired` on fields):**
    *   *Status:* **Not recommended anymore.**
    *   *Why?* It makes unit testing difficult (requires reflection or Spring context) and hides the required dependencies of a class.
2.  **Constructor Injection:**
    *   *Status:* **Recommended approach.**
    *   *Why?* Dependencies are passed through the class constructor, making them explicit and easily mockable for testing. Spring identifies the required interface and dynamically injects the correct managed object at runtime.

## 🔀 Resolving Ambiguity with `@Qualifier`

When multiple classes implement the same interface, Spring doesn't know which one to inject, resulting in a `NoUniqueBeanDefinitionException`.

The `@Qualifier("beanName")` annotation resolves this by explicitly telling Spring which specific bean implementation to inject.

---

## 💻 Code Example: Constructor Injection with `@Qualifier`

This example demonstrates how to inject a specific implementation of a `MessageService` into a `NotificationSender` class.

### 1. The Interface
```java
public interface MessageService {
    void sendMessage(String message, String recipient);
}
```

### 2. The Implementations
We create two implementations and name the beans using the `@Service` annotation.

**Email Implementation:**
```java
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("Sending Email to " + recipient + ": " + message);
    }
}
```

**SMS Implementation:**
```java
import org.springframework.stereotype.Service;

@Service("smsService")
public class SmsService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}
```

### 3. The Dependent Class (Constructor Injection)
We use `@Qualifier` alongside constructor injection to specify exactly which service we want Spring to provide.

```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationSender {

    private final MessageService messageService;

    // Constructor Injection with @Qualifier
    // Explicitly tells Spring to inject the "smsService" bean
    public NotificationSender(@Qualifier("smsService") MessageService messageService) {
        this.messageService = messageService;
    }

    public void sendNotification(String message, String recipient) {
        messageService.sendMessage(message, recipient);
    }
}
```
*Note: The `@Autowired` annotation on the constructor is optional in Spring 4.3+ if the class only has one constructor.*