package edu.testsoftware.criaturasSaltitantes.simulationV1.simulation;

import edu.testsoftware.criaturasSaltitantes.simulationV1.cluster.Cluster;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.event.events.SDL_TextInputEvent;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.video.SDL_Window;
import edu.testsoftware.criaturasSaltitantes.simulationV1.criatura.Criatura;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.github.libsdl4j.api.Sdl.SDL_Init;
import static io.github.libsdl4j.api.Sdl.SDL_Quit;
import static io.github.libsdl4j.api.SdlSubSystemConst.SDL_INIT_EVERYTHING;
import static io.github.libsdl4j.api.error.SdlError.SDL_GetError;
import static io.github.libsdl4j.api.event.SDL_EventType.SDL_QUIT;
import static io.github.libsdl4j.api.event.SdlEvents.SDL_PollEvent;
import static io.github.libsdl4j.api.messagebox.SDL_MessageBoxFlags.SDL_MESSAGEBOX_INFORMATION;
import static io.github.libsdl4j.api.messagebox.SdlMessagebox.SDL_ShowSimpleMessageBox;
import static io.github.libsdl4j.api.render.SDL_RendererFlags.SDL_RENDERER_ACCELERATED;
import static io.github.libsdl4j.api.render.SdlRender.*;
import static io.github.libsdl4j.api.render.SdlRender.SDL_RenderPresent;
import static io.github.libsdl4j.api.timer.SdlTimer.SDL_Delay;
import static io.github.libsdl4j.api.timer.SdlTimer.SDL_GetTicks;
import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_RESIZABLE;
import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_SHOWN;
import static io.github.libsdl4j.api.video.SdlVideo.SDL_CreateWindow;
import static io.github.libsdl4j.api.video.SdlVideoConst.SDL_WINDOWPOS_CENTERED;

public class ProcessamentoCriaturas {

    public static final int WINDOW_WIDTH = 1368;
    public static final int WINDOW_HEIGHT = 768;
    public static final int FPS = 60;
    public static final int FRAME_DELAY = 1000/FPS;

    public static int processamento(int quantidadeCriaturas, int tempoExecucao) {
        if (quantidadeCriaturas < 2) {
            SDL_ShowSimpleMessageBox(
                    SDL_MESSAGEBOX_INFORMATION,
                    "Info",
                    "Quantidade de criaturas inferior ao necessário. Mínimo: 2 criaturas",
                    null);
            return 0;
        }
        if (quantidadeCriaturas > 200) {
            SDL_ShowSimpleMessageBox(
                    SDL_MESSAGEBOX_INFORMATION,
                    "Info",
                    "Quantidade de criaturas acima do máximo. Máximo: 200 criaturas",
                    null);
            return 0;
        }

        initSDL();

        SDL_Window window = createWindow();
        SDL_Renderer renderer = createRenderer(window);
        Criatura[] criaturas = gerarCriaturas(quantidadeCriaturas,0.99);

        int notRobbedCreatures = loopPrincipal(renderer, criaturas,tempoExecucao);

        mostrarResultadosFinais(criaturas);

        SDL_Quit();
        return notRobbedCreatures;
    }
    private static void initSDL() {
        int result = SDL_Init(SDL_INIT_EVERYTHING);

    }

    private static SDL_Window createWindow() {
        SDL_Window window = SDL_CreateWindow("Criaturas Saltitantes", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
                WINDOW_WIDTH, WINDOW_HEIGHT, SDL_WINDOW_SHOWN | SDL_WINDOW_RESIZABLE);

        return window;
    }

    private static SDL_Renderer createRenderer(SDL_Window window) {
        SDL_Renderer renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);

        SDL_RenderClear(renderer);
        SDL_RenderPresent(renderer);
        return renderer;
    }

    public static Criatura[] gerarCriaturas(int quantidade, double randomNumberInput) {
        Random random = new Random();
        Criatura[] criaturas = new Criatura[quantidade];
        double randomNumber = randomNumberInput;

        boolean getAnotherRandomNumber = true;

        while(getAnotherRandomNumber){
            if(randomNumber!=0){
                getAnotherRandomNumber = false;
            }
            randomNumber = random.nextDouble(-1, 1);
        }

        for (int i = 0; i < quantidade; i++) {
            byte r = (byte) random.nextInt(256);
            byte g = (byte) random.nextInt(256);
            byte b = (byte) random.nextInt(256);

            criaturas[i] = new Criatura(
                    random.nextInt(WINDOW_WIDTH - Criatura.CRIATURA_LARGURA),
                    random.nextInt(WINDOW_HEIGHT - Criatura.CRIATURA_ALTURA),
                    2f, 0.1f, r, g, b, (byte) 255, randomNumber);

            evitarSobreposicao(criaturas, i, random);
        }

        for (Criatura c : criaturas) {
            c.move();
            c.hasCollision = false;
        }

        return criaturas;
    }

    public static void evitarSobreposicao(Criatura[] criaturas, int i, Random random) {
        if (i == 0) return;
        boolean precisaNovaPosicao = true;

        while (precisaNovaPosicao) {
            precisaNovaPosicao = false;
            for (int j = 0; j < i; j++) {
                if (criaturas[i].checkCollison(
                        criaturas[i].getCollisionBox(),
                        criaturas[j].getCollisionBox(),
                        Criatura.MIN_SPACE_BTW_BOXES)) {
                    criaturas[i].setPosX(random.nextInt(WINDOW_WIDTH - Criatura.CRIATURA_LARGURA));
                    criaturas[i].setPosY(random.nextInt(WINDOW_HEIGHT - Criatura.CRIATURA_ALTURA));
                    precisaNovaPosicao = true;
                    break;
                }
            }
        }
    }

    public static int loopPrincipal(SDL_Renderer renderer, Criatura[] criaturas, int tempoExecucao) {
        SDL_Event evt = new SDL_Event();
        boolean shouldRun = true;
        int frameTime, frameStart;
        int notRobbedCreatures = criaturas.length;
        boolean shouldCreateCluster = false;

        while (shouldRun) {
            frameStart = SDL_GetTicks();
            notRobbedCreatures = 0;

            shouldCreateCluster = !shouldCreateCluster;

            while (SDL_PollEvent(evt) != 0) {
                switch (evt.type) {
                    case SDL_QUIT:
                        shouldRun = false;
                }
            }

            for (Criatura c : criaturas) {
                c.move();
            }

            // Colisão e lógica de moedas
            for (int i = 0; i < criaturas.length; i++) {
                for (int j = 0; j < criaturas.length; j++) {
                  if (i != j &&
                      !criaturas[i].hasCollision &&
                      !criaturas[j].hasCollision &&
                      criaturas[i].checkClusterColision(
                            criaturas[i],
                            criaturas[j])) {

                      if (criaturas[i].getCluster() == null && criaturas[j].getCluster() == null && shouldCreateCluster) {
                          Cluster novoCluster = new Cluster();
                          criaturas[j].hasCollision = true;
                          criaturas[j].consumedByCluster = true;
                          novoCluster.addCriatura(criaturas[j]);
                          novoCluster.setMoedasDoCluster(criaturas[i].getMoedas()+criaturas[j].getMoedas());
                          criaturas[i].cluster = novoCluster;
                      } else if (criaturas[i].getCluster() != null && criaturas[j].getCluster() == null && shouldCreateCluster) {
                          if(criaturas[i].getCluster().getCriaturas().size()<4){
                              criaturas[j].hasCollision = true;
                              criaturas[j].consumedByCluster = true;
                              criaturas[i].getCluster().addCriatura(criaturas[j]);
                              criaturas[i].getCluster().receiveCoins(criaturas[j].getMoedas());
                          }
                      } else if (criaturas[i].getCluster() == null && criaturas[j].getCluster() != null && shouldCreateCluster) {
                          if(criaturas[j].getCluster().getCriaturas().size()<4) {
                              criaturas[i].hasCollision = true;
                              criaturas[i].consumedByCluster = true;
                              criaturas[j].getCluster().addCriatura(criaturas[i]);
                              criaturas[j].getCluster().receiveCoins(criaturas[i].getMoedas());
                          }
                      }
                      else if(criaturas[i].getCluster()!=criaturas[j].getCluster()){
                          tratarColisao(criaturas, i, j);
                      }
                      else{
                          tratarColisao(criaturas, i, j);
                      }

                    //notRobbedCreatures--;
                    for(Criatura c : criaturas) {
                        if(!c.hasCollision){
                            notRobbedCreatures++;
                        }
                    }

                    if (notRobbedCreatures == 1) {
                      SDL_Delay(1000);
                      shouldRun = false;
                    }
                      break;
                  }
                }
                if(SDL_GetTicks()>=tempoExecucao*1000){
                    SDL_ShowSimpleMessageBox(
                            SDL_MESSAGEBOX_INFORMATION,
                            "Info",
                            "Tempo de execução excedido",
                            null);
                    return 0;
                }
            }

            SDL_SetRenderDrawColor(renderer, (byte) 0, (byte) 0, (byte) 0, (byte) 255);
            SDL_RenderClear(renderer);
            for (Criatura c : criaturas) {
                c.render(renderer);
            }
            SDL_RenderPresent(renderer);

            frameTime = SDL_GetTicks() - frameStart;
            if (FRAME_DELAY > frameTime) {
                SDL_Delay(FRAME_DELAY - frameTime);
            }
        }
        return notRobbedCreatures;
    }

    private static void tratarColisao(Criatura[] criaturas, int i, int j) {
        float dx = (criaturas[i].getCollisionBox().x + Criatura.CRIATURA_LARGURA / 2F)
                - (criaturas[j].getCollisionBox().x + Criatura.CRIATURA_LARGURA / 2F);
        float dy = (criaturas[i].getCollisionBox().y + Criatura.CRIATURA_ALTURA / 2F)
                - (criaturas[j].getCollisionBox().y + Criatura.CRIATURA_ALTURA / 2F);

        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        dx /= distance;
        dy /= distance;

        float vxRel = criaturas[i].getVelX() - criaturas[j].getVelX();
        float vyRel = criaturas[i].getVelY() - criaturas[j].getVelY();

        float dot = vxRel * dx + vyRel * dy;

        criaturas[i].setVelX(criaturas[i].getVelX() - dot * dx);
        criaturas[i].setVelY(criaturas[i].getVelY() - dot * dy);
        criaturas[j].setVelX(criaturas[j].getVelX() + dot * dx);
        criaturas[j].setVelY(criaturas[j].getVelY() + dot * dy);


        System.out.println("Criatura " + i + " roubou " + criaturas[j].getMoedas() / 2 + " moedas da criatura " + j);
        criaturas[j].hasCollision = true;
        int tempQuantidadeMoedas = criaturas[j].giveCoins();


        if(criaturas[i].getCluster()!=null && criaturas[j].getCluster()!=null){
            tempQuantidadeMoedas = criaturas[j].getCluster().giveCoins();
            criaturas[i].getCluster().receiveCoins(tempQuantidadeMoedas);
        }
        if(criaturas[i].getCluster()!=null && criaturas[j].getCluster()==null){
            criaturas[i].getCluster().receiveCoins(tempQuantidadeMoedas);
        }
        else criaturas[i].receiveCoins(tempQuantidadeMoedas);
    }

    private static void mostrarResultadosFinais(Criatura[] criaturas) {
        StringBuilder stb = new StringBuilder();
        DecimalFormat dc = new DecimalFormat("#.##");
        stb.append("Valores de xi finais das criaturas\n");
        for (int i = 0; i < criaturas.length; i++) {
            stb.append("Criatura ").append(i)
                    .append("\n\tQuantidade de moedas: ").append(dc.format(criaturas[i].getMoedas())).append(";\n")
                    .append("\tx").append(i).append(" <- ")
                    .append(dc.format(criaturas[i].getLastXi())).append(" + ")
                    .append(dc.format(criaturas[i].getRandom())).append(" * ")
                    .append(dc.format(criaturas[i].getMoedas())).append(" = ")
                    .append(dc.format(criaturas[i].getXi())).append("\n");

            if(criaturas[i].getCluster()!=null){
                stb.append("Quantidade de moedas do cluster: ")
                   .append(criaturas[i].getCluster().getMoedasDoCluster())
                   .append("\n");
            }
            stb.append("\n");
        }

        SDL_ShowSimpleMessageBox(SDL_MESSAGEBOX_INFORMATION, "Info", stb.toString(), null);
    }

}