#!/usr/bin/env bash
# =============================================================================
# setup-branch-protection.sh
#
# Sets up branch protection rules for the 'main' branch using the GitHub CLI.
#
# Prerequisites:
#   1. Install GitHub CLI: https://cli.github.com/
#        macOS:   brew install gh
#        Windows: winget install --id GitHub.cli
#        Linux:   https://github.com/cli/cli/blob/trunk/docs/install_linux.md
#
#   2. Authenticate with GitHub:
#        gh auth login
#      Follow the prompts to log in with your browser.
#
#   3. Run this script from the root of the repository:
#        bash scripts/setup-branch-protection.sh
# =============================================================================

set -euo pipefail

BRANCH="main"

echo "Setting up branch protection for '${BRANCH}'..."

gh api \
  repos/:owner/:repo/branches/${BRANCH}/protection \
  --method PUT \
  --header "Accept: application/vnd.github+json" \
  --field "required_status_checks[strict]=true" \
  --field "required_status_checks[contexts][]=Build" \
  --field "required_pull_request_reviews[required_approving_review_count]=1" \
  --field "required_pull_request_reviews[dismiss_stale_reviews]=true" \
  --field "enforce_admins=true" \
  --field "restrictions=null" \
  --field "allow_squash_merge=true" \
  --field "allow_merge_commit=false" \
  --field "allow_rebase_merge=false" \
  --field "delete_branch_on_merge=true"

echo "Done! Branch protection rules applied to '${BRANCH}':"
echo "  - Pull request required before merging"
echo "  - At least 1 approving review required"
echo "  - Stale reviews dismissed on new commits"
echo "  - 'Build' status check must pass"
echo "  - Branch must be up to date before merging"
echo "  - Admins are included in these rules"
echo "  - Squash merge only (merge commits and rebase disabled)"
echo "  - Merged branches are auto-deleted"
