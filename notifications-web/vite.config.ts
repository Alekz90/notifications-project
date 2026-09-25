import path from "path"
import tailwindcss from "@tailwindcss/vite"
import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), tailwindcss()],
  resolve: {
    alias: {
      "@":      path.resolve(__dirname, "./src"),
      "@public":  path.resolve(__dirname, "./src/app/public"),
      "@management":  path.resolve(__dirname, "./src/app/management"),
      "@auth":  path.resolve(__dirname, "./src/app/public/auth"),
      "@notifications":  path.resolve(__dirname, "./src/app/management/notifications"),
      "@utils": path.resolve(__dirname, "./src/app/classes/utils"),
      "@enums": path.resolve(__dirname, "./src/app/classes/utils/enums"),
      "@interfaces": path.resolve(__dirname, "./src/app/classes/interfaces"),
      "@services": path.resolve(__dirname, "./src/app/classes/services"),
      "@mocks": path.resolve(__dirname, "./src/app/classes/mocks"),
      "@context": path.resolve(__dirname, "./src/app/context"),
      "@components": path.resolve(__dirname, "./src/app/components"),
    },
  },
  server: {
    proxy: {
      '/notifications': 'http://localhost:8082',
      '/push-notifications': 'http://localhost:8083',
    }
  }
})
