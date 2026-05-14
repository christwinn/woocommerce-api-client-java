# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

### [TARGET-API-VERSION.MAJOR.MINOR.BUGFIX]

## [3.1.3.1] - 2025-29-07
- Updated jackson-core dependency to 2.21.1 as Dependabot detected vulnerability

## [3.1.3.0] - 2025-29-07
- Introduced Optional request/response types

## [1.0.1] - 2025-29-07
- Deflated distributed JAR, removed build-with-dependancies

## [1.0.0] - 2025-29-07

### Added
- CompletableFutures on communications (Rest)
- Enabled POJO and List<POJO> adding to Batch
- Added additional pre-checking on Batch items to ensure they meet pre-requisites

### Changed
- Location of models. these were dangling, pulled into uk.co.twinn.api.woocommerce namespace


## [0.1.1] - 2025-07-25

### Added 
- Orders setState() missed that somehow

## [0.1.0] - 2025-07-25

### Added
- Initial release
