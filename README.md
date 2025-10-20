# AI-Assisted Java & Spring Boot Migration

A comprehensive demonstration of using **Claude Code** for enterprise Java migration, showcasing the power of AI-assisted code modernization while emphasizing the critical importance of **human oversight and validation**.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Problem Statement](#problem-statement)
- [Migration Objectives](#migration-objectives)
- [Why AI-Assisted Migration?](#why-ai-assisted-migration)
- [The Human-in-the-Loop Approach](#the-human-in-the-loop-approach)
- [Repository Structure](#repository-structure)
- [Migration Process Overview](#migration-process-overview)
- [Manual Review Checkpoints](#manual-review-checkpoints)
- [Benefits & Outcomes](#benefits--outcomes)
- [Key Learnings](#key-learnings)
- [Getting Started](#getting-started)
- [References](#references)

---

## Project Overview

This repository demonstrates a **real-world enterprise Java migration** from legacy versions to modern frameworks using AI-assisted coding tools. The project migrates the **Transaction History Service**, a Spring Boot microservice from the Bank of Anthos sample architecture, showcasing systematic modernization with AI assistance while maintaining code quality, security, and reliability through strategic human oversight.

**Migration Scope:**
- **Spring Boot**: 2.3.1.RELEASE → 3.4.4
- **Java**: 8 → 17
- **Spring Cloud**: Hoxton.SR5 → 2023.0.0
- **Namespace Migration**: javax.* → jakarta.*
- **Dependencies**: Security updates for Log4j, Guava, Mockito, and more

**Key Highlight:** This migration leverages Claude Code's specialized agents and slash commands to automate repetitive tasks while strategically inserting human validation at critical decision points.

---

## Problem Statement

### The Challenge

Enterprise Java applications built on older frameworks face significant technical debt:

1. **Security Vulnerabilities**: Legacy dependencies contain known CVEs (e.g., Log4j 2.13.3 → CVE-2021-44228)
2. **Lack of Modern Features**: Missing Java 17 improvements (records, pattern matching, sealed classes)
3. **Framework EOL**: Spring Boot 2.x reached end-of-life, leaving applications unsupported
4. **Namespace Changes**: Jakarta EE migration requiring systematic javax.* → jakarta.* transformations
5. **Breaking Changes**: Spring Boot 3.x introduced API changes requiring careful refactoring

### Traditional Migration Pain Points

Manual migration is:
- **Time-Consuming**: 40-80 hours for a small-to-medium microservice
- **Error-Prone**: Easy to miss edge cases, deprecated APIs, or security issues
- **Tedious**: Repetitive namespace changes across hundreds of files
- **Knowledge-Intensive**: Requires deep understanding of both old and new frameworks
- **Risk-Heavy**: Breaking changes can introduce subtle bugs

### The Question

**Can AI-assisted tools accelerate migration while maintaining quality and safety?**

This project answers: **Yes, with the right human-in-the-loop process.**

---

## Migration Objectives

### Primary Goals

1. **Modernize Framework Stack**
   - Upgrade to Spring Boot 3.4.4 for continued support and features
   - Migrate to Java 17 for performance improvements and modern syntax
   - Update Spring Cloud to 2023.0.0 for cloud-native enhancements

2. **Eliminate Security Vulnerabilities**
   - Address known CVEs in dependencies
   - Update to latest secure versions (Log4j 2.23.1, etc.)
   - Generate SBOM (Software Bill of Materials) for compliance

3. **Maintain Functional Integrity**
   - Zero regression in business logic
   - All existing tests passing
   - API contracts unchanged

4. **Improve Code Quality**
   - Apply modern Java 17 idioms where appropriate
   - Increase test coverage (target: >80%)
   - Adhere to SOLID principles and Spring best practices

5. **Demonstrate AI-Assisted Workflow**
   - Showcase Claude Code's migration capabilities
   - Document human review checkpoints
   - Provide reusable templates for similar migrations

---

## Why AI-Assisted Migration?

### The AI Advantage

AI coding assistants like Claude Code excel at migration tasks because they:

1. **Automate Repetitive Work**
   - Namespace transformations (javax.* → jakarta.*)
   - Dependency version updates across pom.xml
   - Consistent code pattern replacements

2. **Leverage Deep Knowledge**
   - Trained on Spring Boot migration patterns
   - Understand breaking changes between versions
   - Know common pitfalls and solutions

3. **Provide Systematic Analysis**
   - Scan entire codebases for deprecated APIs
   - Identify security vulnerabilities
   - Generate comprehensive reports

4. **Execute Specialized Tasks**
   - Custom agents for security auditing, test generation, and build validation
   - Slash commands for complex multi-step operations
   - Iterative error resolution

5. **Scale Efficiency**
   - Reduce 40-80 hour migrations to 4-8 hours of human time
   - Consistent quality across all changes
   - Comprehensive documentation automatically generated

### Claude Code Capabilities Used

This migration leverages:

- **Specialized Agents**:
  - `java-migration-specialist`: Core migration logic
  - `security-auditor`: OWASP Top 10 and CVE scanning
  - `test-engineer`: Coverage analysis and test generation
  - `code-reviewer`: Quality and best practices validation

- **Custom Slash Commands**:
  - `/analyze-project`: Pre-migration risk assessment
  - `/migrate-springboot`: Systematic migration execution
  - `/validate-build`: Iterative compilation error fixing
  - `/security-sast`: Static application security testing
  - `/security-dependencies`: Dependency vulnerability scanning
  - `/review-code`: Comprehensive code quality review

- **AI-Powered Tools**:
  - Semantic code understanding
  - Context-aware refactoring
  - Pattern-based transformations
  - Automated test generation

---

## The Human-in-the-Loop Approach

### Why Human Oversight is Critical

While AI excels at automation, **human judgment remains irreplaceable** for:

1. **Strategic Decisions**
   - Choosing migration timing and scope
   - Evaluating risk vs. reward trade-offs
   - Prioritizing which issues to fix vs. accept

2. **Domain-Specific Knowledge**
   - Understanding business logic implications
   - Validating functional equivalence
   - Assessing operational impact

3. **Quality Assurance**
   - Reviewing generated code for correctness
   - Ensuring adherence to team standards
   - Validating security recommendations

4. **Context and Nuance**
   - Handling edge cases AI might miss
   - Making judgment calls on ambiguous situations
   - Ensuring solutions fit architectural patterns

### The 100% Automated Trap

**Important**: This project intentionally **avoids 100% automation**. Fully automated migrations risk:
- Blindly applying changes without understanding impact
- Missing business-critical edge cases
- Introducing subtle bugs or security issues
- Creating technical debt through suboptimal solutions
- Loss of institutional knowledge

**Our Principle**: AI automates the tedious; humans validate the critical.

---

## Repository Structure

```
ai-assisted-code-migration/
├── README.md                    # This file - project overview and guide
├── MIGRATION.md                 # Detailed step-by-step migration process
├── CLAUDE.md                    # Claude Code project instructions
├── LICENSE                      # Project license
│
├── legacy-code/                 # Original Spring Boot 2.3.1 / Java 8 code
│   ├── README.md               # Service documentation (pre-migration)
│   ├── pom.xml                 # Maven configuration (legacy versions)
│   ├── src/main/java/          # Application source code
│   ├── src/main/resources/     # Configuration files
│   └── src/test/java/          # Test suite (pre-migration)
│
├── migrated-code/               # Migrated Spring Boot 3.4.4 / Java 17 code
│   ├── README.md               # Service documentation (post-migration)
│   ├── pom.xml                 # Maven configuration (updated versions)
│   ├── src/main/java/          # Migrated application source code
│   ├── src/main/resources/     # Updated configuration files
│   └── src/test/java/          # Updated and enhanced test suite
│
└── .claude/                     # Claude Code configuration
    ├── commands/               # Custom slash commands
    │   ├── analyze-project.md
    │   ├── migrate-springboot.md
    │   ├── validate-build.md
    │   ├── test-generate.md
    │   ├── security-sast.md
    │   ├── security-dependencies.md
    │   ├── security-review.md
    │   └── review-code.md
    │
    └── agents/                 # Specialized AI agents
        ├── java-migration-specialist.md
        ├── security-auditor.md
        ├── test-engineer.md
        └── code-reviewer.md
```

### Key Directories

- **`legacy-code/`**: Baseline Spring Boot 2.3.1 application for comparison
- **`migrated-code/`**: Successfully migrated Spring Boot 3.4.4 application
- **`.claude/commands/`**: Reusable slash commands for systematic migration
- **`.claude/agents/`**: Specialized AI agents with domain expertise

---

## Migration Process Overview

The migration follows an **8-phase systematic approach** orchestrated by Claude Code:

### Phase 1: Pre-Migration Analysis (AI + Human Review)

**AI Tasks:**
- Scan codebase for files, packages, and dependencies
- Identify javax.* usage and deprecated APIs
- Calculate migration complexity score
- Generate risk assessment report

**Human Review:**
- Evaluate risk score and decide go/no-go
- Review estimated effort and timeline
- Identify business-critical areas requiring extra care
- Approve migration strategy

**Outcome**: Migration feasibility report with risk score and recommendations

---

### Phase 2: Core Migration (AI-Automated)

**AI Tasks:**
- Update pom.xml dependencies (Spring Boot, Java, Spring Cloud)
- Transform javax.* → jakarta.* across all source files
- Update Spring Security configuration for Spring Boot 3.x
- Migrate test dependencies (JUnit 5, Mockito 5.x)
- Apply deprecated API replacements

**Human Review:**
- Verify dependency version selections
- Review namespace transformation completeness
- Check for business logic changes (should be none)
- Validate Spring Security configuration

**Outcome**: Codebase updated to Spring Boot 3.4.4 / Java 17 (may not compile yet)

---

### Phase 3: Build Validation (AI-Iterative with Human Checkpoints)

**AI Tasks:**
- Attempt compilation and capture errors
- Classify errors by type (namespace, API, plugin)
- Apply fixes systematically
- Re-compile and verify (iterative loop)

**Human Review:**
- Review each batch of fixes before proceeding
- Validate that fixes don't alter business logic
- Approve when build succeeds

**Outcome**: Clean compilation with zero errors

---

### Phase 4: Test Generation & Enhancement (AI + Human Validation)

**AI Tasks:**
- Run JaCoCo coverage analysis
- Identify untested classes and methods
- Generate JUnit 5 tests for gaps
- Create integration tests
- Verify all tests pass

**Human Review:**
- Review generated test quality and relevance
- Ensure tests validate actual business logic
- Add domain-specific test cases AI might miss
- Verify coverage meets team standards (>80%)

**Outcome**: Enhanced test suite with comprehensive coverage

---

### Phase 5: Static Security Analysis (AI + Human Triage)

**AI Tasks:**
- Run SpotBugs with FindSecBugs plugin
- Execute PMD with security rules
- Scan for common vulnerabilities (SQL injection, XSS, etc.)
- Generate findings report by severity

**Human Review:**
- Triage findings (true positives vs. false positives)
- Evaluate risk for each vulnerability
- Decide which to fix vs. accept with justification
- Approve security fixes

**Outcome**: SAST report with security issues addressed or accepted

---

### Phase 6: Dependency Security Scan (AI + Human Decisioning)

**AI Tasks:**
- Run OWASP Dependency-Check
- Identify CVEs in dependencies
- Generate SBOM (CycloneDX format)
- Recommend version updates

**Human Review:**
- Review CVE severity and exploitability
- Check for breaking changes in dependency updates
- Decide on version update strategy
- Approve dependency changes

**Outcome**: Zero critical/high CVEs, SBOM generated

---

### Phase 7: Comprehensive Security Review (AI + Human Approval)

**AI Tasks:**
- Analyze OWASP Top 10 compliance
- Review Spring Security configuration
- Check authentication/authorization mechanisms
- Validate input sanitization
- Assess cryptographic implementations

**Human Review:**
- Review security posture holistically
- Validate recommendations against threat model
- Ensure security controls don't break functionality
- Sign off on security improvements

**Outcome**: OWASP Top 10 compliant with documented security posture

---

### Phase 8: Code Quality Review (AI + Human Final Approval)

**AI Tasks:**
- Analyze code against SOLID principles
- Check Spring Boot best practices
- Identify design pattern issues
- Validate documentation completeness
- Run checkstyle validation

**Human Review:**
- Review architectural recommendations
- Ensure code maintainability
- Validate adherence to team standards
- Final approval for merge

**Outcome**: High-quality, maintainable code ready for production

---

## Manual Review Checkpoints

### Critical Human Validation Points

Throughout the migration, **human review is mandatory** at these checkpoints:

#### 1. Risk Assessment (Phase 1)

**What to Review:**
- Migration complexity score (Low/Medium/High/Very High)
- Estimated effort vs. available resources
- Business impact and timing considerations

**Decision Point:** Proceed with migration or defer?

**Why Human Judgment Matters:** Only humans understand business priorities, team capacity, and strategic timing.

---

#### 2. Dependency Version Selection (Phase 2)

**What to Review:**
- Chosen versions for Spring Boot, Spring Cloud, dependencies
- Breaking changes in selected versions
- Compatibility matrix across dependencies

**Decision Point:** Approve version selections or request adjustments?

**Why Human Judgment Matters:** Version choices impact stability, security, and long-term maintainability. AI may not know organization-specific compatibility requirements.

---

#### 3. Code Transformation Validation (Phase 2)

**What to Review:**
- Namespace changes (javax.* → jakarta.*)
- Spring Security configuration changes
- Deprecated API replacements

**Decision Point:** Are transformations semantically equivalent?

**Why Human Judgment Matters:** AI might miss subtle behavior changes. Humans must verify business logic remains intact.

---

#### 4. Compilation Fix Review (Phase 3)

**What to Review:**
- Each batch of compilation fixes applied
- Ensure fixes don't introduce logic changes
- Verify fixes follow team patterns

**Decision Point:** Approve fixes and continue or request revision?

**Why Human Judgment Matters:** Automated fixes might solve compilation errors but introduce runtime bugs. Human review ensures correctness.

---

#### 5. Test Quality Validation (Phase 4)

**What to Review:**
- Generated test coverage and relevance
- Test assertions validate actual business logic
- Edge cases are properly tested

**Decision Point:** Are tests sufficient or do we need additional scenarios?

**Why Human Judgment Matters:** AI-generated tests may achieve coverage metrics but miss critical business scenarios only domain experts understand.

---

#### 6. Security Finding Triage (Phases 5-7)

**What to Review:**
- Each security finding (critical, high, medium, low)
- Context-specific risk assessment
- Feasibility and impact of proposed fixes

**Decision Points:**
- Which vulnerabilities are true positives?
- Which fixes should be prioritized?
- Which risks can be accepted with justification?

**Why Human Judgment Matters:** Security findings require contextual understanding. Not all reported issues are exploitable in the application's specific context. Humans must evaluate real-world risk.

---

#### 7. Code Quality and Architecture (Phase 8)

**What to Review:**
- Architectural recommendations
- Code maintainability and readability
- Adherence to team and organization standards

**Decision Point:** Final approval for production deployment?

**Why Human Judgment Matters:** Code quality is subjective and context-dependent. Humans ensure the solution fits the team's coding culture and long-term vision.

---

### Documentation of Review Decisions

For each checkpoint, document:
1. **What was reviewed**: Specific changes or findings examined
2. **Decision made**: Approved, modified, or rejected
3. **Rationale**: Why this decision was appropriate
4. **Reviewer**: Who performed the review
5. **Timestamp**: When the review occurred

**Example:**
```
Checkpoint: Security Finding Triage (Phase 5)
Reviewed: SpotBugs finding "Potential SQL Injection in TransactionRepository"
Decision: Accepted Risk
Rationale: Repository uses JPA @Query with named parameters. Inputs are
          validated by Spring Security JWT claims. Risk is low in this context.
Reviewer: Jane Doe (Security Engineer)
Timestamp: 2025-01-20 14:30 UTC
```

---

## Benefits & Outcomes

### Efficiency Gains

**Traditional Manual Migration:**
- Estimated Time: 120-160 hours
- Error Rate: High (easy to miss files, imports, edge cases)
- Consistency: Variable (depends on developer experience)
- Documentation: Often incomplete

**AI-Assisted Migration with Claude Code:**
- Total Human Time: 15-20 hours (strategic reviews)
- Total AI Time: 20-25 hours (automated transformations)
- Error Rate: Low (systematic, comprehensive coverage)
- Consistency: High (same logic applied everywhere)
- Documentation: Automatically generated at each phase

---

### Quality Improvements

1. **Comprehensive Security Scanning**
   - AI-powered SAST with multiple tools (SpotBugs, PMD, Semgrep)
   - Dependency vulnerability scanning (OWASP Dependency-Check)
   - OWASP Top 10 compliance validation
   - SBOM generation for supply chain security

2. **Enhanced Test Coverage**
   - JaCoCo coverage analysis identifies gaps
   - AI generates missing unit tests
   - Integration tests for critical paths
   - Achieved >80% code coverage (up from ~60%)

3. **Code Quality Enforcement**
   - SOLID principles validation
   - Spring Boot best practices applied
   - Checkstyle compliance verified
   - JavaDoc added to public APIs

4. **Consistency Across Codebase**
   - Uniform application of migration patterns
   - Consistent naming conventions
   - Standardized error handling
   - No "half-migrated" inconsistencies

---

## Key Learnings

### What Worked Well

1. **Specialized Agents**: Using domain-specific agents (java-migration-specialist, security-auditor) provided expert-level guidance.

2. **Phased Approach**: Breaking migration into 8 distinct phases with clear success criteria prevented overwhelming complexity.

3. **Strategic Human Review**: Inserting review checkpoints at critical junctures caught issues AI couldn't anticipate.

4. **Slash Commands**: Custom commands encapsulated complex workflows, making them repeatable and shareable.

5. **Iterative Validation**: The `/validate-build` command's iterative approach systematically resolved compilation errors.

6. **Comprehensive Security**: Multi-layer security scanning (SAST + dependency + manual review) caught vulnerabilities at different levels.

---

### Challenges and Solutions

**Challenge 1: AI Generated Tests Lacking Business Context**

- **Problem**: Generated tests achieved coverage but didn't validate critical business rules.
- **Solution**: Human review added domain-specific test cases; AI provided template.
- **Lesson**: AI excels at structure; humans provide domain knowledge.

**Challenge 2: Dependency Version Conflicts**

- **Problem**: Some dependency updates introduced transitive conflicts.
- **Solution**: Human decided on version pinning vs. exclusions based on project needs.
- **Lesson**: Complex dependency management requires human judgment.

**Challenge 3: Over-Aggressive Modernization**

- **Problem**: Initial AI suggestions introduced Java 17 features extensively.
- **Solution**: Human review opted for conservative approach to minimize risk.
- **Lesson**: Modernization enthusiasm must be balanced with stability needs.

**Challenge 4: Security False Positives**

- **Problem**: SAST tools flagged many non-issues.
- **Solution**: Human triage separated true positives from false positives.
- **Lesson**: Security findings require contextual understanding.

---

### Best Practices for AI-Assisted Migration

1. **Start with Analysis**: Always run pre-migration analysis to understand scope and risk.

2. **Commit Incrementally**: Commit after each phase to enable rollback if needed.

3. **Review in Context**: Don't just review diffs; understand the business logic being changed.

4. **Test Continuously**: Run tests after each phase to catch regressions early.

5. **Document Decisions**: Record why you accepted or rejected AI recommendations.

6. **Trust but Verify**: AI is excellent but not infallible. Validate critical changes.

7. **Keep Humans in the Loop**: Never blindly accept automated changes to production code.

---

## Getting Started

### Prerequisites

- **Claude Code CLI**: Install from [https://claude.com/code](https://claude.com/code)
- **Java 17**: Required for migrated code
- **Maven 3.8+**: For building the project
- **Git**: For version control

### Running the Migration

1. **Clone this repository:**
   ```bash
   git clone <repository-url>
   cd ai-assisted-code-migration
   ```

2. **Review the legacy code:**
   ```bash
   cd legacy-code
   ./mvnw clean test  # Verify baseline
   cat README.md      # Understand the service
   ```

3. **Follow the migration guide:**
   ```bash
   cat MIGRATION.md   # Detailed step-by-step instructions
   ```

4. **Execute migration with Claude Code:**
   ```bash
   # Start in project root
   cd ai-assisted-code-migration

   # Run each phase (see MIGRATION.md for details)
   /analyze-project
   /migrate-springboot
   /validate-build
   /test-generate
   /security-sast
   /security-dependencies
   /security-review
   /review-code
   ```

5. **Review migrated code:**
   ```bash
   cd migrated-code
   ./mvnw clean verify  # All tests should pass
   cat README.md        # Review updated documentation
   ```

### Adapting for Your Project

To use this approach for your own Java/Spring Boot migration:

1. **Copy `.claude/` directory** to your project root
2. **Customize agents** in `.claude/agents/` for your tech stack
3. **Adjust slash commands** in `.claude/commands/` for your workflow
4. **Create `CLAUDE.md`** with your project-specific instructions
5. **Follow the 8-phase process** from MIGRATION.md

---

## References

### Project Documentation

- **[MIGRATION.md](./MIGRATION.md)**: Complete step-by-step migration guide with detailed instructions for each phase
- **[legacy-code/README.md](./legacy-code/README.md)**: Documentation for the original Spring Boot 2.3.1 application
- **[migrated-code/README.md](./migrated-code/README.md)**: Documentation for the migrated Spring Boot 3.4.4 application
- **[CLAUDE.md](./CLAUDE.md)**: Claude Code instructions and project context

### Claude Code Resources

- **Claude Code Documentation**: [https://docs.claude.com/en/docs/claude-code](https://docs.claude.com/en/docs/claude-code)
- **Claude Code GitHub**: Issues and discussions
- **Custom Slash Commands**: See `.claude/commands/` directory
- **Specialized Agents**: See `.claude/agents/` directory

### Spring Boot Migration Resources

- **Spring Boot 3.0 Migration Guide**: [https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide)
- **Spring Boot 3.4 Release Notes**: [https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.4-Release-Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.4-Release-Notes)
- **Jakarta EE 9 Migration**: [https://jakarta.ee/specifications/platform/9/](https://jakarta.ee/specifications/platform/9/)

### Security Resources

- **OWASP Top 10**: [https://owasp.org/www-project-top-ten/](https://owasp.org/www-project-top-ten/)
- **OWASP Dependency-Check**: [https://owasp.org/www-project-dependency-check/](https://owasp.org/www-project-dependency-check/)
- **CycloneDX SBOM**: [https://cyclonedx.org/](https://cyclonedx.org/)

---

## Conclusion

This project demonstrates that **AI-assisted migration can dramatically accelerate enterprise Java modernization** while maintaining—and even improving—code quality and security. The key is strategic human oversight at critical decision points.

**The Future of Migration:**
- AI handles the tedious, repetitive work (namespace changes, dependency updates)
- Humans provide strategic direction and validation
- Together, they achieve better results than either could alone

**Core Principles:**
1. AI accelerates but doesn't replace human judgment
2. Systematic processes produce reliable outcomes
3. Human-in-the-loop ensures safety and quality
4. Documentation and knowledge capture multiply value

**The Result:** A migration process that is **faster, more consistent, and higher quality** than traditional manual approaches, while keeping humans in control of critical decisions.

---

**Project Maintainers**: AI-Assisted Code Migration Team
**Last Updated**: 2025-01-20
**License**: See [LICENSE](./LICENSE)
**Questions/Issues**: Create a GitHub issue or discussion

---

**Ready to migrate?** Start with [MIGRATION.md](./MIGRATION.md) for step-by-step instructions.
