<template>
  <body>
    <div class="container">
      <div class="card">
        <h2>Dashboard</h2>
        <div class="summary">
          <div>
            <h5>Total Balance</h5>
            <p>$3,800</p>
          </div>

          <div>
            <h5>Total Expenses</h5>
            <p>$1,200</p>
          </div>

          <div>
            <h5>Total Income</h5>
            <p>$2,500</p>
          </div>

          <div>
            <h5>Total Savings</h5>
            <p>$1,300</p>
          </div>
        </div>
      </div>
      <div>
        <div class="button-container">
          <q-btn
            @click="goToCalculator"
            label="Go to Calculator"
            color="primary"
          />
          <q-btn
            @click="goToRecords"
            label="Go to Financial Records"
            color="primary"
          />
        </div>
      </div>
    </div>
  </body>
</template>

<script>
export default {
  methods: {
    goToCalculator() {
      this.$router.push({ path: "/calculator" });
    },
    goToRecords() {
      this.$router.push({ path: "/history" });
    },
  },
};
</script>

<style scoped>
.button-container {
  text-align: left;
  margin-top: 25px;
  display: flex;
  justify-content: space-evenly;
}
</style>
