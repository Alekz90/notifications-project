

import { ArrowLeft, BellOff, Home } from "lucide-react"
import { Link, useNavigate } from "react-router"
import { Button } from "@/components/ui/button"
import { RouteConstants } from "@utils/route-constants"

export const NotFoundPage = () => {
  const navigate = useNavigate()

  return (
    <main className="relative flex min-h-[calc(100vh-5rem)] w-full items-center justify-center overflow-hidden px-6 pb-20">
      <div className="absolute inset-x-0 top-1/2 -z-10 h-px bg-border" />
      <div className="absolute left-1/2 top-0 -z-10 h-full w-px -translate-x-1/2 bg-border" />
      <div className="absolute -left-24 top-16 -z-10 size-64 rounded-full border border-border" />
      <div className="absolute -right-20 bottom-8 -z-10 size-52 rounded-full border border-border" />

      <section className="max-w-xl text-center">
        <div className="mx-auto mb-8 flex size-16 items-center justify-center rounded-2xl border border-border bg-background shadow-sm">
          <BellOff className="size-7 text-muted-foreground" strokeWidth={1.5} />
        </div>
        <p className="mb-4 text-sm font-semibold tracking-[0.2em] text-muted-foreground">ERROR 404</p>
        <div className="relative mb-5">
          <span className="pointer-events-none absolute inset-x-0 top-1/2 -z-10 h-5 -translate-y-1/2 bg-muted" />
          <h1 className="text-8xl font-bold tracking-normal text-foreground sm:text-9xl">404</h1>
        </div>
        <h2 className="text-2xl font-semibold tracking-normal text-foreground sm:text-3xl">This notification went missing.</h2>
        <p className="mx-auto mt-4 max-w-md text-base leading-7 text-muted-foreground">
          The page you requested may have moved, expired, or never existed.
        </p>
        <div className="mt-8 flex flex-col justify-center gap-3 sm:flex-row">
          <Button onClick={() => navigate(-1)} variant="outline" className="w-full sm:w-auto">
            <ArrowLeft />
            Go back
          </Button>
          <Link
            to={RouteConstants.auth.toPath}
            className="inline-flex h-9 w-full items-center justify-center gap-1.5 rounded-md bg-primary px-2.5 text-sm font-medium text-primary-foreground transition-colors hover:bg-primary/80 sm:w-auto"
          >
            <Home className="size-4" />
            Return home
          </Link>
        </div>
      </section>
    </main>
  )
}
