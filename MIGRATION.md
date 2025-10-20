# Spring Boot 2.x → 3.x Migration Guide

This guide provides step-by-step instructions for migrating the Transaction History Service from Spring Boot 2.3.1 (Java 8) to Spring Boot 3.4.4 (Java 17) using Claude Code's AI-assisted migration tools.

## 📋 Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Migration Phases](#migration-phases)
- [Detailed Phase Instructions](#detailed-phase-instructions)
- [Troubleshooting](#troubleshooting)
- [Rollback Procedures](#rollback-procedures)
- [Validation Checklist](#validation-checklist)

---

## Overview

**Current State:**
- Spring Boot: 2.3.1.RELEASE
- Java: 1.8
- Spring Cloud: Hoxton.SR5
- Dependencies: Log4j 2.13.3, Guava 28.2-jre, Mockito 2.7.2

**Target State:**
- Spring Boot: 3.4.4
- Java: 17
- Spring Cloud: 2023.0.0
- Updated dependencies with security fixes

**Migration Strategy:** AI-assisted systematic migration using Claude Code slash commands and specialized agents.

---

## Prerequisites

### 1. Backup Current State

```bash
# Create a backup branch
git checkout -b backup-spring-boot-2.3.1
git push origin backup-spring-boot-2.3.1

# Return to main branch
git checkout main

# Create migration branch
git checkout -b migration-spring-boot-3.4.4
```

### 2. Verify Claude Code Setup

Ensure you have the following slash commands available:

```bash
# List available slash commands
ls .claude/commands/

# Expected commands:
# - analyze-project.md
# - migrate-springboot.md
# - validate-build.md
# - test-generate.md
# - security-sast.md
# - security-dependencies.md
# - security-review.md
# - review-code.md
```

### 3. Verify Agents

```bash
# List available agents
ls .claude/agents/

# Expected agents:
# - java-migration-specialist.md
# - code-reviewer.md
# - security-auditor.md
# - test-engineer.md
```

### 4. Baseline Testing

```bash
# Document current state
./mvnw clean test > baseline-test-results.txt
./mvnw verify > baseline-build-results.txt
```

---

## Migration Phases

The migration consists of 8 phases executed sequentially:

| Phase | Command | Purpose | Est. Time |
|-------|---------|---------|-----------|
| 1 | `/analyze-project` | Assess migration complexity | 5-10 min |
| 2 | `/migrate-springboot` | Execute core migration | 30-60 min |
| 3 | `/validate-build` | Fix compilation errors | 15-30 min |
| 4 | `/test-generate` | Generate missing tests | 20-40 min |
| 5 | `/security-sast` | Static security analysis | 10-15 min |
| 6 | `/security-dependencies` | Dependency vulnerability scan | 10-15 min |
| 7 | `/security-review` | Comprehensive security review | 15-20 min |
| 8 | `/review-code` | Code quality review | 15-20 min |

**Total Estimated Time:** 2-4 hours

---

## Detailed Phase Instructions

### Phase 1: Project Analysis

**Command:** `/analyze-project`

**Purpose:** Generate comprehensive pre-migration analysis including risk assessment and effort estimation.

**Steps:**

1. Start Claude Code in your project directory:
   ```bash
   cd /path/to/ai-assisted-code-migration
   ```

2. Run the analysis command:
   ```
   /analyze-project
   ```

3. Review the generated report which includes:
   - Project metrics (files, LOC, packages)
   - Technology stack inventory
   - Migration complexity score
   - Namespace migration impact
   - Deprecated API usage
   - Effort estimation
   - Migration roadmap

4. **Decision Point:** Review the risk score and recommendations:
   - **Low (0-20)**: Proceed with confidence
   - **Medium (21-50)**: Proceed with caution, allocate extra time
   - **High (51-100)**: Consider phased approach
   - **Very High (100+)**: Seek senior review

5. Save the analysis report:
   ```bash
   # The report will be displayed by Claude
   # Copy and save to migration-analysis-report.md for reference
   ```

**Expected Output:**
- Risk assessment score
- File-by-file impact analysis
- Dependency update plan
- Estimated effort (13-22 hours for this project)

---

### Phase 2: Core Migration

**Command:** `/migrate-springboot`

**Purpose:** Execute systematic migration of dependencies, source code, and tests.

**Steps:**

1. Ensure you're on the migration branch:
   ```bash
   git status  # Verify branch
   ```

2. Run the migration command:
   ```
   /migrate-springboot
   ```

3. The `java-migration-specialist` agent will execute 6 phases:

   **Phase 1: Pre-Migration Analysis**
   - Reads pom.xml
   - Scans for javax.* imports
   - Documents current state

   **Phase 2: Dependency Updates (pom.xml)**
   - Updates Spring Boot parent to 3.4.4
   - Changes Java version to 17
   - Updates Spring Cloud to 2023.0.0
   - Updates critical dependencies (Log4j, Guava, etc.)
   - Updates Maven plugins

   **Phase 3: Source Code Migration**
   - Replaces javax.* → jakarta.* (automated)
   - Updates Spring Security configuration
   - Fixes deprecated APIs
   - Optionally applies Java 17 features

   **Phase 4: Test Migration**
   - Updates JUnit 4 → 5 (if needed)
   - Updates Mockito usage
   - Fixes test dependencies

   **Phase 5: Validation**
   - Attempts compilation
   - Identifies remaining errors

   **Phase 6: Final Report**
   - Summarizes changes
   - Lists files modified
   - Documents known issues

4. Review the migration summary and commit initial changes:
   ```bash
   git add .
   git commit -m "chore: migrate to Spring Boot 3.4.4 and Java 17

   - Update Spring Boot parent to 3.4.4
   - Update Java version to 17
   - Update Spring Cloud to 2023.0.0
   - Migrate javax.* → jakarta.* namespaces
   - Update Spring Security configuration
   - Update test dependencies

   Generated with Claude Code /migrate-springboot"
   ```

**Expected Output:**
- Updated pom.xml with new versions
- Source files with jakarta.* imports
- Updated Security configuration
- Migration summary report

**Checkpoint:** At this point, the project may not compile yet. That's expected - we'll fix compilation errors in Phase 3.

---

### Phase 3: Build Validation

**Command:** `/validate-build`

**Purpose:** Iteratively fix compilation errors until clean build is achieved.

**Steps:**

1. Run the build validation command:
   ```
   /validate-build
   ```

2. The `build-validator` agent will:
   - Attempt `./mvnw clean compile`
   - Classify errors by type
   - Apply fixes systematically
   - Recompile after each fix
   - Track progress (errors remaining)

3. Monitor the iteration progress:
   - Iteration 1: Namespace fixes
   - Iteration 2: API deprecation fixes
   - Iteration 3: Plugin compatibility
   - Iteration 4: Final adjustments

4. Review each fix applied and verify:
   ```bash
   # Check diff of changes
   git diff

   # Review specific file changes
   git diff src/main/java/anthos/samples/bankofanthos/transactionhistory/
   ```

5. Once compilation succeeds, commit:
   ```bash
   git add .
   git commit -m "fix: resolve compilation errors post-migration

   - Fix remaining javax/jakarta namespace issues
   - Update deprecated API usage
   - Fix plugin compatibility issues

   Build now compiles successfully
   Generated with Claude Code /validate-build"
   ```

**Expected Output:**
- Zero compilation errors
- Clean `./mvnw compile` execution
- Build validation report

**Success Criteria:**
```bash
# This should succeed
./mvnw clean compile

# Expected output: BUILD SUCCESS
```

---

### Phase 4: Test Generation & Validation

**Command:** `/test-generate`

**Purpose:** Generate missing tests and ensure comprehensive coverage.

**Steps:**

1. Run coverage analysis and test generation:
   ```
   /test-generate
   ```

2. The `test-engineer` agent will:
   - Run existing tests with JaCoCo coverage
   - Identify untested classes and methods
   - Generate JUnit 5 tests for gaps
   - Generate Spring Boot integration tests
   - Verify all tests pass

3. Review generated tests:
   ```bash
   # Check new test files
   find src/test -name "*Test.java" -newer pom.xml

   # Review generated test quality
   cat src/test/java/anthos/samples/bankofanthos/transactionhistory/TransactionCacheTest.java
   ```

4. Run full test suite:
   ```bash
   ./mvnw clean test
   ```

5. Verify coverage improvement:
   ```bash
   ./mvnw verify
   open target/site/jacoco/index.html
   ```

6. Commit generated tests:
   ```bash
   git add src/test
   git commit -m "test: generate comprehensive test coverage

   - Add unit tests for TransactionCache
   - Add unit tests for LedgerReader
   - Add controller tests for TransactionHistoryController
   - Coverage increased from X% to Y%

   Generated with Claude Code /test-generate"
   ```

**Expected Output:**
- New test files generated
- All tests passing
- Coverage report showing improvement (target: >80%)

**Success Criteria:**
```bash
# All tests should pass
./mvnw test
# Expected: Tests run: X, Failures: 0, Errors: 0

# Coverage should be >80%
./mvnw verify
# Check target/site/jacoco/index.html
```

---

### Phase 5: Static Security Analysis

**Command:** `/security-sast`

**Purpose:** Run static analysis tools to identify security vulnerabilities.

**Steps:**

1. Run SAST analysis:
   ```
   /security-sast
   ```

2. The `security-auditor` agent will execute:
   - SpotBugs with FindSecBugs plugin
   - PMD with security rules
   - Semgrep (if available)
   - Manual pattern detection for common vulnerabilities

3. Review security findings:
   - Critical: SQL injection, command injection
   - High: Hardcoded secrets, weak crypto
   - Medium: Missing input validation
   - Low: Code quality issues

4. Address critical and high severity issues:
   ```bash
   # Example fixes based on findings
   # - Replace MD5 with BCrypt
   # - Parameterize SQL queries
   # - Remove hardcoded credentials
   ```

5. Re-run SAST to verify fixes:
   ```bash
   ./mvnw spotbugs:check
   ./mvnw pmd:check
   ```

6. Commit security fixes:
   ```bash
   git add .
   git commit -m "security: address SAST findings

   - Fix SQL injection vulnerability in UserService
   - Replace MD5 with BCrypt for password hashing
   - Remove hardcoded credentials

   Generated with Claude Code /security-sast"
   ```

**Expected Output:**
- SAST scan report with findings by severity
- Remediation recommendations
- Zero critical/high findings after fixes

---

### Phase 6: Dependency Security Scan

**Command:** `/security-dependencies`

**Purpose:** Identify and fix vulnerable dependencies.

**Steps:**

1. Run dependency security scan:
   ```
   /security-dependencies
   ```

2. The `dependency-analyzer` agent will:
   - Run OWASP Dependency-Check
   - Generate SBOM (CycloneDX format)
   - Identify CVEs in dependencies
   - Provide remediation plan

3. Review vulnerability report:
   - Focus on Critical/High CVEs
   - Check for known exploits
   - Verify fix availability

4. Apply dependency fixes:
   ```xml
   <!-- Example: Override vulnerable version -->
   <properties>
       <log4j2.version>2.23.1</log4j2.version>
   </properties>
   ```

5. Re-run dependency check:
   ```bash
   ./mvnw dependency-check:check
   ```

6. Commit dependency updates:
   ```bash
   git add pom.xml
   git commit -m "security: update vulnerable dependencies

   - Update Log4j to 2.23.1 (CVE-2021-44228)
   - Update Guava to 33.0.0-jre
   - SBOM generated in target/bom.json

   Generated with Claude Code /security-dependencies"
   ```

**Expected Output:**
- Dependency vulnerability report
- SBOM file (target/bom.json)
- Zero critical/high CVEs

---

### Phase 7: Comprehensive Security Review

**Command:** `/security-review`

**Purpose:** Perform holistic security analysis including OWASP Top 10.

**Steps:**

1. Run comprehensive security review:
   ```
   /security-review
   ```

2. The `security-auditor` agent will analyze:
   - Spring Security configuration
   - OWASP Top 10 compliance
   - Authentication/Authorization
   - Input validation
   - Cryptographic implementations
   - Sensitive data handling
   - Container security (if applicable)

3. Review security report organized by severity:
   - 🔴 Critical (CVSS 9.0-10.0)
   - 🟠 High (CVSS 7.0-8.9)
   - 🟡 Medium (CVSS 4.0-6.9)
   - 🟢 Low (CVSS 0.1-3.9)

4. Address findings by priority:
   ```bash
   # Example fixes:
   # - Add @PreAuthorize to controller methods
   # - Enable CSRF protection
   # - Add input validation with @Valid
   ```

5. Document security improvements:
   ```bash
   # Create security review report
   cat > security-review-report.md << EOF
   # Security Review Summary
   - Critical issues: 0
   - High issues: 0
   - Medium issues: 2 (accepted risk)
   - Low issues: 5 (future improvements)
   EOF
   ```

6. Commit security improvements:
   ```bash
   git add .
   git commit -m "security: comprehensive security hardening

   - Add method-level authorization checks
   - Enable CSRF protection for stateful endpoints
   - Add input validation to all @RequestBody
   - Implement rate limiting

   Generated with Claude Code /security-review"
   ```

**Expected Output:**
- Comprehensive security report
- OWASP Top 10 compliance status
- Remediation guidance with code examples

---

### Phase 8: Code Quality Review

**Command:** `/review-code`

**Purpose:** Ensure code quality, maintainability, and architectural integrity.

**Steps:**

1. Run code quality review:
   ```
   /review-code
   ```

2. The `code-reviewer` agent will analyze:
   - Java coding standards
   - SOLID principles compliance
   - Spring Framework best practices
   - Design patterns usage
   - Performance considerations
   - Test quality
   - Documentation

3. Review findings by priority:
   - 🔴 Critical: Must fix before merge
   - 🟠 High: Should fix
   - 🟡 Medium: Consider addressing
   - 🟢 Low/Nice to have

4. Address critical and high priority issues:
   ```bash
   # Example improvements:
   # - Refactor God object
   # - Add missing JavaDoc
   # - Fix code duplication
   # - Improve method naming
   ```

5. Run checkstyle validation:
   ```bash
   ./mvnw checkstyle:check
   ```

6. Commit code quality improvements:
   ```bash
   git add .
   git commit -m "refactor: address code review findings

   - Refactor TransactionCache for single responsibility
   - Add JavaDoc to public APIs
   - Remove code duplication in LedgerReader
   - Improve error handling with specific exceptions

   Generated with Claude Code /review-code"
   ```

**Expected Output:**
- Code quality report with actionable feedback
- Checkstyle compliance
- Architectural recommendations

---

## Final Validation

### 1. Full Build Cycle

```bash
# Clean build
./mvnw clean

# Compile
./mvnw compile

# Run tests
./mvnw test

# Package
./mvnw package

# Verify (includes coverage)
./mvnw verify
```

**Expected Results:**
- ✅ BUILD SUCCESS at each step
- ✅ All tests pass
- ✅ Coverage >80%
- ✅ JAR artifact created

### 2. Run Application Locally

```bash
# Set required environment variables
export PORT=8080
export VERSION=3.4.4
export LOCAL_ROUTING_NUM=123456789
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/ledger
export SPRING_DATASOURCE_USERNAME=admin
export SPRING_DATASOURCE_PASSWORD=password

# Run application
./mvnw spring-boot:run
```

**Verify:**
- Application starts without errors
- Health check responds: `curl http://localhost:8080/healthy`
- Ready check responds: `curl http://localhost:8080/ready`

### 3. Docker Image Build (Optional)

```bash
# Build Docker image with Jib
./mvnw compile jib:dockerBuild

# Verify image
docker images | grep transactionhistory

# Test container
docker run -p 8080:8080 transactionhistory:1.0
```

### 4. Final Commit

```bash
git add .
git commit -m "chore: complete Spring Boot 3.4.4 migration

Migration Summary:
- Spring Boot 2.3.1 → 3.4.4
- Java 8 → 17
- Spring Cloud Hoxton.SR5 → 2023.0.0
- All tests passing (X tests)
- Code coverage: Y%
- Security vulnerabilities: 0 critical/high
- Build time: Xs

Migration completed using Claude Code AI-assisted tools"
```

---

## Troubleshooting

### Common Issues

#### Issue 1: Compilation Errors After Migration

**Symptoms:** Errors like "package javax.persistence does not exist"

**Solution:**
```bash
# Re-run validation
/validate-build

# Or manually check for missed imports
grep -r "import javax\." src --include="*.java"
```

#### Issue 2: Tests Failing After Migration

**Symptoms:** MockitoException, NoSuchMethodError

**Solution:**
```bash
# Check Mockito migration
grep -r "MockitoAnnotations.initMocks" src/test --include="*.java"

# Should be:
# MockitoAnnotations.openMocks(this)
```

#### Issue 3: Dependency Conflicts

**Symptoms:** "dependency convergence error"

**Solution:**
```bash
# Analyze dependency tree
./mvnw dependency:tree

# Exclude conflicting transitive dependency
<exclusions>
  <exclusion>
    <groupId>...</groupId>
    <artifactId>...</artifactId>
  </exclusion>
</exclusions>
```

#### Issue 4: Spring Security Configuration Not Working

**Symptoms:** 403 Forbidden on all endpoints

**Solution:**
```bash
# Verify SecurityFilterChain bean
grep -r "SecurityFilterChain" src --include="*.java"

# Check security configuration follows Spring Boot 3 pattern
```

---

## Rollback Procedures

### If Migration Fails

1. **Return to backup branch:**
   ```bash
   git checkout backup-spring-boot-2.3.1
   git branch -D migration-spring-boot-3.4.4
   ```

2. **Verify original state:**
   ```bash
   ./mvnw clean test
   # Should pass as before
   ```

### If Issues Found Post-Deployment

1. **Revert to previous version:**
   ```bash
   # If using Docker
   docker pull transactionhistory:2.3.1
   kubectl set image deployment/transactionhistory transactionhistory=transactionhistory:2.3.1
   ```

2. **Document issues for next attempt:**
   ```bash
   # Create rollback report
   cat > rollback-report.md << EOF
   # Rollback Report
   - Issue: [Description]
   - Root cause: [Analysis]
   - Fix required: [Action items]
   EOF
   ```

---

## Validation Checklist

Use this checklist to verify successful migration:

### Pre-Migration
- [ ] Backup branch created
- [ ] Baseline tests documented
- [ ] Analysis report generated and reviewed
- [ ] Team notified of migration start

### Post-Migration
- [ ] All phases completed (1-8)
- [ ] Zero compilation errors
- [ ] All tests passing
- [ ] Code coverage >80%
- [ ] Zero critical/high security vulnerabilities
- [ ] Checkstyle compliant
- [ ] Application starts successfully
- [ ] Health endpoints responding
- [ ] Docker image builds (if applicable)

### Code Quality
- [ ] No God objects or anti-patterns
- [ ] SOLID principles followed
- [ ] Spring best practices applied
- [ ] JavaDoc added to public APIs
- [ ] No code duplication

### Security
- [ ] OWASP Top 10 compliant
- [ ] No hardcoded secrets
- [ ] Input validation implemented
- [ ] Authorization checks in place
- [ ] Dependency vulnerabilities resolved
- [ ] SBOM generated

### Documentation
- [ ] CLAUDE.md updated (if needed)
- [ ] README.md updated
- [ ] Migration notes documented
- [ ] Rollback procedure documented

---

## Success Metrics

**Migration Successful When:**
- ✅ Spring Boot version: 3.4.4
- ✅ Java version: 17
- ✅ Build status: SUCCESS
- ✅ Test pass rate: 100%
- ✅ Code coverage: >80%
- ✅ Security vulnerabilities: 0 critical, 0 high
- ✅ Application starts: < 30 seconds
- ✅ No regression in functionality

---

## Next Steps After Migration

1. **Performance Testing**
   - Run load tests
   - Compare metrics with Spring Boot 2.x
   - Optimize if needed

2. **Staging Deployment**
   - Deploy to staging environment
   - Run integration tests
   - Verify with QA team

3. **Production Deployment**
   - Follow blue-green deployment
   - Monitor logs and metrics
   - Have rollback plan ready

4. **Documentation**
   - Update deployment docs
   - Share lessons learned
   - Update team wiki

---

## Support & Resources

- **Claude Code Documentation**: https://docs.claude.com/en/docs/claude-code
- **Spring Boot 3 Migration Guide**: https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide
- **Jakarta EE Migration**: https://jakarta.ee/specifications/
- **Project Issues**: Create GitHub issue for problems

---

**Migration Template Version:** 1.0
**Last Updated:** 2025-01-20
**Maintained By:** AI-Assisted Code Migration Team
