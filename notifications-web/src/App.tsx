import { UserContextProvider } from './app/context/UserContext'
import { AppRouter } from './AppRouter'
import { RouterProvider } from 'react-router'
import { TooltipProvider } from './components/ui/tooltip'


function App() {
  return (
    <TooltipProvider>
      <UserContextProvider>
        <RouterProvider router={AppRouter}/>
      </UserContextProvider>
    </TooltipProvider>
  )
}

export default App
